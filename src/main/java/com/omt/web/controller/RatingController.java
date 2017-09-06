package com.omt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.Watchlist;
import com.omt.domain.Rating;
import com.omt.service.RatingService;
import com.omt.service.WatchlistService;


@RestController
@RequestMapping("/ratings")
public class RatingController {

	RatingService ratingService;
	WatchlistService watchlistService;

	@Autowired
	public RatingController(RatingService ratingService, WatchlistService watchlistService) {
		this.ratingService = ratingService;
		this.watchlistService = watchlistService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Rating> findAll() {
		return ratingService.findAll();
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public Rating findOne(@PathVariable("id") Long id) {
		return ratingService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Rating save(@RequestBody Rating rating) {
//		Rating rate = new Rating();
//		rate.setRateMark(rating.getRateMark());
		return ratingService.save(rating);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Rating update(@RequestBody Rating rating) {
		Rating rate = new Rating();
		rate.setRateMark(rating.getRateMark());
		return ratingService.save(rating);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		ratingService.delete(id);
	}

	@RequestMapping(path = "remove-by-watchlist-id/{watchlist_id}", method = RequestMethod.DELETE)
	public void deleteFormer(@PathVariable("watchlist_id") Long id) {
		List<Rating> ratings = ratingService.findByWatchlistId(id);
		if(ratings != null) {
			for(Rating rating : ratings) {
				ratingService.delete(rating.getId());				
			}
		}
	}
	
	@RequestMapping(path = "find-rating-by-watchlist-id/{watchlist_id}", method = RequestMethod.GET)
	public Rating findRatingByWatchListId(@PathVariable("watchlist_id") Long id) {
		return ratingService.findRatingByWatchListId(id);
	}
}
