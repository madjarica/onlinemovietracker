package com.omt.service;

import java.util.List;

import com.omt.domain.Rating;
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

    public List<Watchlist> findByUsername(String username){
        return watchlistRepository.findByWatchlistUser(username);
    }

    public Watchlist checkForDuplicate(String username, Long id){
        return watchlistRepository.findByWatchlistUserAndVideoId(username, id);
    }

    public List<Watchlist> findByTitle(String search, String username){
        return watchlistRepository.findByVideoTitleContainsAndWatchlistUser(search, username);
    }

    public Integer averageRating(Long id){
        List<Watchlist> watchlists = watchlistRepository.findByVideoId(id);
        int number = 0;
        int sum = 0;
        for (Watchlist watchlist: watchlists) {
            for(Rating rating: watchlist.getRating()){
                number++;
                sum += rating.getRateMark();
            }
        }
        if(number != 0) {
            return sum / number;
        }
        return 0;
    }

}