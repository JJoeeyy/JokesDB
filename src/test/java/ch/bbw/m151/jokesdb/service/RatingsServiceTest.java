package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.controller.RatingsController;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RatingsServiceTest implements WithAssertions {

    @Autowired
    RatingsController ratingsController;

    @Autowired
    RatingsService ratingsService;

    @Test
    void saveRating(){
        ratingsService.saveOrUpdateRating(2,1);

        assertThat(ratingsService.checkAlreadyExist(1)).isTrue();
    }
}