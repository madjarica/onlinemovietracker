
package com.omt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.Rating;
import com.omt.repository.RatingRepository;


@Service
public class RatingService {

	RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll() {
        // TODO Auto-generated method stub
        return ratingRepository.findAll();
    }

    public Rating save(Rating rating) {
        // TODO Auto-generated method stub
        return ratingRepository.save(rating);
    }

    public Rating findOne(Long id) {
        // TODO Auto-generated method stub
        return ratingRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        ratingRepository.delete(id);
    }

    public List<Rating> findByWatchlistId(Long id) {
        return ratingRepository.findByWatchListId(id);
    }
    
    public Rating findRatingByWatchListId(Long id) {
    	return ratingRepository.findRatingByWatchListId(id);
    }
    
}