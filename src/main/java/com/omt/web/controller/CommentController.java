package com.omt.web.controller;

import java.util.List;

import com.omt.service.AdminMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.Comment;
import com.omt.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	 CommentService commentService;
	 AdminMessageService adminMessageService;

	    @Autowired
	    public CommentController(CommentService commentService, AdminMessageService adminMessageService) {
	        this.commentService = commentService;
	        this.adminMessageService = adminMessageService;
	    }
	    
	    
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	    @RequestMapping(method = RequestMethod.GET)
	    public List<Comment> findAll() {
	        return commentService.findAll();
	    }
	    
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	    @RequestMapping(path = "{id}", method = RequestMethod.GET)
	    public Comment findOne(@PathVariable("id") Long id) {
	        return commentService.findOne(id);
	    }
	    
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	    @RequestMapping(method = RequestMethod.POST)
	    public Comment save(@RequestBody Comment comment) {
	        return commentService.save(comment);
	    }
	    
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	    @RequestMapping(method = RequestMethod.PUT)
	    public Comment update(@RequestBody Comment comment) {
	        return commentService.save(comment);
	    }
	    
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable("id") Long id) {
			adminMessageService.deleteForComments(id);
	        commentService.delete(id);
	    }
	    
}
