package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.RatingsEntity;
import ch.bbw.m151.jokesdb.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;

    public void saveOrUpdateRating(int rating, int jokeId){
        List<RatingsEntity> ratingsEntityList = ratingsRepository.findAll();
        RatingsEntity newRating = new RatingsEntity();
        newRating.setRating(rating);
        newRating.setId(jokeId);

        for (RatingsEntity singleRatingEntity : ratingsEntityList){
            if (singleRatingEntity.getId() == jokeId){
                newRating.setCreatedOn(singleRatingEntity.getCreatedOn());
            }
        }

        ratingsRepository.save(newRating);
    }

    public boolean checkAlreadyExist(int jokeId){
        List<RatingsEntity> ratingsEntityList = ratingsRepository.findAll();
        boolean exist = false;

        for (RatingsEntity singleRatingEntity : ratingsEntityList){
            if (singleRatingEntity.getId() == jokeId){
                exist = true;
            }
        }

        return exist;
    }
}
