package com.omt.service;

import com.omt.domain.Genre;
import com.omt.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    
    GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        // TODO Auto-generated method stub
        return genreRepository.findAll();
    }

    public Genre save(Genre genre) {
        // TODO Auto-generated method stub
        return genreRepository.save(genre);
    }

    public Genre findOne(Long id) {
        // TODO Auto-generated method stub
        return genreRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        genreRepository.delete(id);
    }
}
