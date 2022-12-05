package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class RemoteJokesService {

    @Autowired
    private JokesRepository jokesRepository;

    @Data
    public static class JokeDto {
        boolean error;
        String category;
        String type;
        String setup;
        String delivery;
        String joke;
        Flags flags;
        int id;
        boolean safe;
        String lang;
    }

    @Data
    public static class Flags{
        boolean nsfw;
        boolean religious;
        boolean political;
        boolean racist;
        boolean sexist;
        boolean explicit;
    }

    @EventListener(ContextRefreshedEvent.class)
    private void saveJoke(){
        ArrayList<JokesEntity> jokesEntities = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            JokeDto joke = getJoke();

            JokesEntity jokesEntity = new JokesEntity();

            jokesEntity.setJoke(joke.getJoke());
            jokesEntity.setCategory(joke.getCategory());
            jokesEntity.setType(joke.getType());
            jokesEntity.setSetup(joke.getSetup());
            jokesEntity.setDelivery(joke.getDelivery());
            jokesEntity.setSafe(joke.isSafe());
            jokesEntity.setLang(joke.getLang());

            jokesEntity.setExplicit(joke.getFlags().explicit);
            jokesEntity.setNsfw(joke.getFlags().nsfw);
            jokesEntity.setExplicit(joke.getFlags().religious);
            jokesEntity.setExplicit(joke.getFlags().political);
            jokesEntity.setExplicit(joke.getFlags().racist);
            jokesEntity.setExplicit(joke.getFlags().sexist);

            int count = 0;
            for (JokesEntity j:jokesEntities){
                if (Objects.equals(j.getJoke(), jokesEntity.getJoke()) && Objects.equals(j.getSetup(), jokesEntity.getSetup())){
                    count++;
                }
            }

            if (count == 0){
                jokesRepository.save(jokesEntity);
                jokesEntities.add(jokesEntity);
            }
        }
    }

    public JokeDto getJoke(){
        WebClient client = WebClient.create("https://v2.jokeapi.dev");

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/joke/programming");

        WebClient.RequestHeadersSpec headersSpec = bodySpec.body(
                BodyInserters.fromPublisher(Mono.just("data"), String.class)
        );

        Mono<JokeDto> response = headersSpec.retrieve()
                .bodyToMono(JokeDto.class);

        JokeDto joke = response.block();

        return joke;
    }
}
