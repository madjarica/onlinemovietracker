package com.omt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.TvShowEpisode;
import com.omt.repository.TvShowEpisodeRepository;

@Service
public class TvShowEpisodeService{
	
	TvShowEpisodeRepository tvShowEpisodeRepository;
	
	
	@Autowired
	public TvShowEpisodeService(TvShowEpisodeRepository tvShowEpisodeRepository) {
		this.tvShowEpisodeRepository = tvShowEpisodeRepository;
	}

	public List<TvShowEpisode> findAll() {
		// TODO Auto-generated method stub
		return tvShowEpisodeRepository.findAll();
	}

	public TvShowEpisode save(TvShowEpisode tvShowEpisode) {
		// TODO Auto-generated method stub
		return tvShowEpisodeRepository.save(tvShowEpisode);
	}

	public TvShowEpisode findOne(Long id) {
		// TODO Auto-generated method stub
		return tvShowEpisodeRepository.findOne(id);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		tvShowEpisodeRepository.delete(id);
	}
	
}
