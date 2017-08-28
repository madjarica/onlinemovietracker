package com.omt.web.controller;

import com.omt.domain.Video;
import com.omt.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
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
}