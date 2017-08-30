package com.omt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.TvShowEpisode;
import com.omt.service.TvShowEpisodeService;

@RestController
@RequestMapping("/episodes")
public class TvShowEpisodeController {
	
	TvShowEpisodeService tvShowEpisodeService;
	
	@Autowired
	public TvShowEpisodeController(TvShowEpisodeService tvShowEpisodeService) {
		this.tvShowEpisodeService = tvShowEpisodeService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<TvShowEpisode> findAll(){
		return tvShowEpisodeService.findAll();
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public TvShowEpisode findOne(@PathVariable("id") Long id){
		return tvShowEpisodeService.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public TvShowEpisode save(@RequestBody TvShowEpisode tvShowEpisode){
		return tvShowEpisodeService.save(tvShowEpisode);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public TvShowEpisode update(@RequestBody TvShowEpisode tvShowEpisode){
		return tvShowEpisodeService.save(tvShowEpisode);
	}
	
	@RequestMapping(path="{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		tvShowEpisodeService.delete(id);
} 
	
	
}
