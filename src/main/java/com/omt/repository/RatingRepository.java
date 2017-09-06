package com.omt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omt.domain.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	@Query(value="SELECT * FROM ratings WHERE watchlist_id like ?1", nativeQuery = true)
	List<Rating> findByWatchListId(Long watchlistId);

	@Query(value="SELECT * FROM ratings WHERE watchlist_id like ?1", nativeQuery = true)
	Rating findRatingByWatchListId(Long watchlistId);
	
}
