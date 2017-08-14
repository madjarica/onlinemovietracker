package com.omt.repository;

import java.util.List;

import com.omt.domain.Person;
import com.omt.domain.TvShowEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omt.domain.TvShow;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {

    public List<TvShow> findAll();

    public TvShow save(TvShow tvShow);

    public TvShow findOne(Long id);

    public void delete(Long id);

    public TvShow findByTmdbTvShowId(Long id);

}
