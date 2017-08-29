package com.omt.repository;

import com.omt.domain.WatchlistCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistCollectionRepository extends JpaRepository<WatchlistCollection, Long>{
    public WatchlistCollection findByUsername(String name);
}
