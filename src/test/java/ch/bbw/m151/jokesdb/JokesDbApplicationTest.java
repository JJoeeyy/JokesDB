package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import ch.bbw.m151.jokesdb.service.RemoteJokesService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class JokesDbApplicationTest implements WithAssertions {

	@Autowired
	JokesRepository jokesRepository;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void jokesAreLoadedAtStartup() {
		var jokes = jokesRepository.findAll();
		assertThat(jokes).hasSizeGreaterThan(100)
				.allSatisfy(x -> assertThat(x.getJoke()).isNotEmpty());
	}

	@Test
	void jokesCanBeRetrievedViaHttpGet() {
		webTestClient.get()
				.uri("/joke/programming")
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBodyList(JokesEntity.class)
				.hasSize(1);
	}

	@Autowired
	RemoteJokesService remoteJokesService;

	@Test
	void motd(){
		System.out.println(remoteJokesService.getJoke());
		System.out.println(remoteJokesService.getJoke());
	}
}
