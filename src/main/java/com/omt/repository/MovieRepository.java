package com.omt.repository;

import com.omt.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    public Movie findByTmdbMovieId(Long id);
}
