package com.omt.repository;

import com.omt.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

    public List<Movie> findAll();

    public Movie save(Movie movie);

    public Movie findOne(Long id);

    public void delete(Long id);
}
