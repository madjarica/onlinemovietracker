package com.omt.repository;

import com.omt.domain.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long>{
    public Watchlist findByLoginUserUsername(String username);
}
