package com.omt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omt.domain.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	
}
