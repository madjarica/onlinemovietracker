package com.omt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.Comment;
import com.omt.repository.CommentRepository;

@Service
public class CommentService {

	CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        // TODO Auto-generated method stub
        return commentRepository.findAll();
    }

    public Comment save(Comment comment) {
        // TODO Auto-generated method stub
        return commentRepository.save(comment);
    }

    public Comment findOne(Long id) {
        // TODO Auto-generated method stub
        return commentRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        commentRepository.delete(id);
    }
	
}
