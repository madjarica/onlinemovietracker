package com.omt.repository;

import com.omt.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long>{
    public List<Character> findByTmdbMediaId(Long id);
}
