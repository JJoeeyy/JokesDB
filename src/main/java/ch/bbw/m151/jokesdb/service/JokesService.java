package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokesService {

    @Autowired
    private JokesRepository jokesRepository;

    public JokesEntity getRandomJoke(){
        int random = (int)(Math.random() * (jokesRepository.findAll().size()));
        return jokesRepository.getReferenceById(random);
    }
}
