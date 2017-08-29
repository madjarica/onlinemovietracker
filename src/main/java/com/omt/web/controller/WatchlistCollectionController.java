package com.omt.web.controller;

import com.omt.domain.WatchlistCollection;
import com.omt.service.WatchlistCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("watchlist-collection")
public class WatchlistCollectionController {

    WatchlistCollectionService watchlistCollectionService;

    @Autowired
    public WatchlistCollectionController(WatchlistCollectionService watchlistCollectionService) {
        this.watchlistCollectionService = watchlistCollectionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<WatchlistCollection> findAll() {
        return watchlistCollectionService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public WatchlistCollection findOne(@PathVariable("id") Long id) {
        return watchlistCollectionService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public WatchlistCollection save(@RequestBody WatchlistCollection watchlistCollection) {
        return watchlistCollectionService.save(watchlistCollection);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public WatchlistCollection update(@RequestBody WatchlistCollection watchlistCollection) {
        return watchlistCollectionService.save(watchlistCollection);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        watchlistCollectionService.delete(id);
    }

    @RequestMapping(path = "find-by-user/{username}", method = RequestMethod.GET)
    public WatchlistCollection findByUser(@PathVariable("username") String username){
        return watchlistCollectionService.findByUsername(username);
    }
    
}
