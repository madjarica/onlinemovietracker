package com.omt.web.controller;

import com.omt.domain.Movie;
import com.omt.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Movie findOne(@PathVariable("id") Long id){
        return movieService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Movie save(@RequestBody Movie movie){
        return movieService.save(movie);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Movie update(@RequestBody Movie movie)throws Exception{
        if("Movie" == movieService.findOne(movie.id).getDtype()) {
            return movieService.save(movie);
        }
        else {
            throw new Exception();
        }
    }

    @RequestMapping(path="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        movieService.delete(id);
    }

}
