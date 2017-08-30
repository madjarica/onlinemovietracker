package com.omt.service;

import com.omt.domain.Video;
import com.omt.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> findAll() {
        // TODO Auto-generated method stub
        return videoRepository.findAll();
    }

    public Video save(Video video) {
        // TODO Auto-generated method stub
        return videoRepository.save(video);
    }

    public Video findOne(Long id) {
        // TODO Auto-generated method stub
        return videoRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        videoRepository.delete(id);
    }

	public List<Video> getPublicVideoByTitle(String title) {
		// TODO Auto-generated method stub
		return videoRepository.findByTitle(title);
	}
}
