package com.omt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.Rating;
import com.omt.service.RatingService;


@RestController
@RequestMapping("/ratings")
public class RatingController {

	 RatingService ratingService;

	    @Autowired
	    public RatingController(RatingService ratingService) {
	        this.ratingService = ratingService;
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
	        return ratingService.save(rating);
	    }

	    @RequestMapping(method = RequestMethod.PUT)
	    public Rating update(@RequestBody Rating rating) {
	        return ratingService.save(rating);
	    }

	    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable("id") Long id) {
	        ratingService.delete(id);
	    }

	
}
