package com.omt.repository;

import com.omt.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long>{

    public List<Keyword> findAll();

    public Keyword save(Keyword keyword);

    public Keyword findOne(Long id);

    public void delete(Long id);

    public Keyword findByName(String name);

}
