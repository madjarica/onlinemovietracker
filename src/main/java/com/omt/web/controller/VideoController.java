package com.omt.web.controller;

import com.omt.domain.Video;
import com.omt.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

	VideoService videoService;

	@Autowired
	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Video> findAll() {
		return videoService.findAll();
	}
	
	/*
	 * Find all visible videos of certain title without logged user	
	 */
	@RequestMapping(path="search/video/{title}", method = RequestMethod.GET)
	public List<Video> getPublicVideoByTitle(@PathVariable("title")String title){
		return videoService.getPublicVideoByTitle(title);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public Video getVideoById(@PathVariable("id") Long id) {
		return videoService.findOne((long) id);
	}
}
