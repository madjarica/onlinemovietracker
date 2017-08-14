package com.omt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.Watchlist;
import com.omt.repository.WatchlistRepository;

@Service
public class WatchlistService {

	WatchlistRepository watchlistRepository;

    @Autowired
    public WatchlistService(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }

    public List<Watchlist> findAll() {
        // TODO Auto-generated method stub
        return watchlistRepository.findAll();
    }

    public Watchlist save(Watchlist watchlist) {
        // TODO Auto-generated method stub
        return watchlistRepository.save(watchlist);
    }

    public Watchlist findOne(Long id) {
        // TODO Auto-generated method stub
        return watchlistRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        watchlistRepository.delete(id);
    }
	
}