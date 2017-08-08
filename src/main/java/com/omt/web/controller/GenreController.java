package com.omt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.Genre;
import com.omt.service.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

    GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Genre findOne(@PathVariable("id") Long id) {
        return genreService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Genre save(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Genre update(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        genreService.delete(id);
    }

}
