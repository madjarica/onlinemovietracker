package com.omt.web.controller;

import com.omt.config.LoginUserService;
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
	private LoginUserService loginUserService;

	@Autowired
	public VideoController(VideoService videoService, LoginUserService loginUserService) {
		this.videoService = videoService;
		this.loginUserService = loginUserService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Video> findAll() {
		return videoService.findAll();
	}
	
	/*
	 * Find all visible videos of certain title without logged user	
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@RequestMapping(path="search/video/{title}", method = RequestMethod.GET)
	public List<Video> getPublicVideoByTitle(@PathVariable("title")String title){
		return videoService.getPublicVideoByTitle(title);
	}
}
