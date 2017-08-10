package com.omt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.Character;
import com.omt.service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Character> findAll() {
        return characterService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Character findOne(@PathVariable("id") Long id) {
        return characterService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Character save(@RequestBody Character character) {
        return characterService.save(character);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Character update(@RequestBody Character character) {
        return characterService.save(character);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        characterService.delete(id);
    }

    public List<Character> findByTmdbMediaId(Long id){
        return characterService.findByTmdbMediaId(id);
    }

}
