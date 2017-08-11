package com.omt.repository;

import com.omt.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    public List<Genre> findAll();

    public Genre save(Genre genre);

    public Genre findOne(Long id);

    public void delete(Long id);

    public Genre findByName(String name);
}
