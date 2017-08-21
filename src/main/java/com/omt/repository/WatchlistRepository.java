package com.omt.repository;

import com.omt.domain.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long>{
    public List<Watchlist> findByWatchlistUser(String username);
    public Watchlist findByWatchlistUserAndVideoId(String username, Long id);
}
