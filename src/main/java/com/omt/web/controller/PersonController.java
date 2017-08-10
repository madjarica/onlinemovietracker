package com.omt.web.controller;

import java.util.List;

import com.omt.domain.Movie;
import com.omt.domain.TvShow;
import com.omt.service.MovieService;
import com.omt.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.Person;
import com.omt.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

    PersonService personService;
    TvShowService tvShowService;
    MovieService movieService;

    @Autowired
    public PersonController(PersonService personService, TvShowService tvShowService, MovieService movieService) {
        this.personService = personService;
        this.tvShowService = tvShowService;
        this.movieService = movieService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Person> findAll() {
        return personService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Person findOne(@PathVariable("id") Long id) {
        return personService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Person update(@RequestBody Person person) {
        return personService.save(person);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        deleteTvShowReference(id);
        deleteMovieReference(id);
        personService.delete(id);
    }

    public void deleteTvShowReference(Long id) {
        List<TvShow> tvShows = tvShowService.findWithThisPerson(id);
        if (!tvShows.isEmpty()) {
            for (int i = 0; i < tvShows.size(); i++) {
                tvShows.get(i).getPersonList().remove(personService.findOne(id));
            }
        }
    }

    public void deleteMovieReference(Long id) {
        List<Movie> movies = movieService.findWithThisPerson(id);
        if (!movies.isEmpty()) {
            for (int i = 0; i < movies.size(); i++) {
                movies.get(i).getPersonList().remove(personService.findOne(id));
            }
        }
    }


}
