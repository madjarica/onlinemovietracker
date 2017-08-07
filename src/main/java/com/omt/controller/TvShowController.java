package com.omt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.TvShow;
import com.omt.service.TvShowService;

@RestController
@RequestMapping("/tvshows")
public class TvShowController {
	
	TvShowService tvShowService;
	
	@Autowired
	public TvShowController(TvShowService tvShowService) {
		this.tvShowService = tvShowService;
	}
	

	@RequestMapping(method = RequestMethod.GET)
	public List<TvShow> findAll(){
		return tvShowService.findAll();
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public TvShow findOne(@PathVariable("id") Long id){
		return tvShowService.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public TvShow save(@RequestBody TvShow tvShow){
		return tvShowService.save(tvShow);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public TvShow update(@RequestBody TvShow tvShow){
		return tvShowService.save(tvShow);
	}
	
	@RequestMapping(path="{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		tvShowService.delete(id);
} 
	
	
}
