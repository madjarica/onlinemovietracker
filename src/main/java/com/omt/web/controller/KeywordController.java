package com.omt.web.controller;

import com.omt.domain.Keyword;
import com.omt.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keywords")
public class KeywordController {

    KeywordService keywordService;

    @Autowired
    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Keyword> findAll() {
        return keywordService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Keyword findOne(@PathVariable("id") Long id) {
        return keywordService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Keyword save(@RequestBody Keyword keyword) {
        return keywordService.save(keyword);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Keyword update(@RequestBody Keyword keyword) {
        return keywordService.save(keyword);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        keywordService.delete(id);
    }
}
