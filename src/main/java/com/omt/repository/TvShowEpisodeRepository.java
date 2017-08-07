package com.omt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omt.domain.TvShowEpisode;

@Repository
public interface TvShowEpisodeRepository extends JpaRepository<TvShowEpisode, Long> {
	
	public List<TvShowEpisode> findAll();

	public TvShowEpisode save(TvShowEpisode tvShowEpisode);

	public TvShowEpisode findOne(Long id);

	public void delete(Long id);

}
