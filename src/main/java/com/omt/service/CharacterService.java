package com.omt.service;

import com.omt.domain.Character;
import com.omt.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> findAll() {
        // TODO Auto-generated method stub
        return characterRepository.findAll();
    }

    public Character save(Character character) {
        // TODO Auto-generated method stub
        return characterRepository.save(character);
    }

    public Character findOne(Long id) {
        // TODO Auto-generated method stub
        return characterRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        characterRepository.delete(id);
    }

    public List<Character> findByTmdbMediaId(Long id){
        return characterRepository.findByTmdbMediaId(id);
    }
}
