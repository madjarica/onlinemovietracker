package com.omt.service;

import com.omt.domain.Movie;
import com.omt.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        // TODO Auto-generated method stub
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        // TODO Auto-generated method stub
        return movieRepository.save(movie);
    }

    public Movie findOne(Long id) {
        // TODO Auto-generated method stub
        return movieRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        movieRepository.delete(id);
    }

    public Movie findByTmdbMovieId(Long id) {
        return movieRepository.findByTmdbMovieId(id);
    }

}
