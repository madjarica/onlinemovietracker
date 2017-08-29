package com.omt.service;

import com.omt.domain.WatchlistCollection;
import com.omt.repository.WatchlistCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistCollectionService {
    WatchlistCollectionRepository watchlistCollectionRepository;

    @Autowired
    public WatchlistCollectionService(WatchlistCollectionRepository watchlistCollectionRepository) {
        this.watchlistCollectionRepository = watchlistCollectionRepository;
    }

    public List<WatchlistCollection> findAll() {
        // TODO Auto-generated method stub
        return watchlistCollectionRepository.findAll();
    }

    public WatchlistCollection save(WatchlistCollection watchlistCollection) {
        // TODO Auto-generated method stub
        return watchlistCollectionRepository.save(watchlistCollection);
    }

    public WatchlistCollection findOne(Long id) {
        // TODO Auto-generated method stub
        return watchlistCollectionRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        watchlistCollectionRepository.delete(id);
    }
    public WatchlistCollection findByUsername(String name){
        return watchlistCollectionRepository.findByUsername(name);
    }
}
