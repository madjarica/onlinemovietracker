package com.omt.repository;

import com.omt.domain.Video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{


	List<Video> findByTitleContaining(String title);

}
