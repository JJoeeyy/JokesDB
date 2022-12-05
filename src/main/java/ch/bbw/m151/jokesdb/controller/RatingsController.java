package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.datamodel.RatingsEntity;
import ch.bbw.m151.jokesdb.service.RatingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatingsController {

    @Autowired
    private final RatingsService ratingsService;

    @PostMapping("/saverating/{rating}/{id}")
    public void saveRating(@PathVariable("rating") int rating, @PathVariable("id") int id){
        ratingsService.saveOrUpdateRating(rating, id);
    }

    @GetMapping("/checkrating/{id}")
    public boolean checkRating(@PathVariable("id") int id){
        return ratingsService.checkAlreadyExist(id);
    }
}
