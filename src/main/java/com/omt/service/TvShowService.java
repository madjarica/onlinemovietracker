package com.omt.service;

import java.util.List;

import com.omt.domain.TvShowEpisode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.TvShow;
import com.omt.repository.TvShowRepository;

@Service
public class TvShowService {

    TvShowRepository tvShowRepository;


    @Autowired
    public TvShowService(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public List<TvShow> findAll() {
        // TODO Auto-generated method stub
        return tvShowRepository.findAll();
    }

    public TvShow save(TvShow tvShow) {
        // TODO Auto-generated method stub
        return tvShowRepository.save(tvShow);
    }

    public TvShow findOne(Long id) {
        // TODO Auto-generated method stub
        return tvShowRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        tvShowRepository.delete(id);
    }

    public TvShow findByTmdbTvShowId(Long id){
        return tvShowRepository.findByTmdbTvShowId(id);
    }

}
