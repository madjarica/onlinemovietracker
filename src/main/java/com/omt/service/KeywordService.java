package com.omt.service;

import com.omt.domain.Keyword;
import com.omt.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {

    KeywordRepository keywordRepository;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<Keyword> findAll() {
        // TODO Auto-generated method stub
        return keywordRepository.findAll();
    }

    public Keyword save(Keyword keyword) {
        // TODO Auto-generated method stub
        return keywordRepository.save(keyword);
    }

    public Keyword findOne(Long id) {
        // TODO Auto-generated method stub
        return keywordRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        keywordRepository.delete(id);
    }

    public Keyword findByName(String name){
        return keywordRepository.findByName(name);
    }
}
