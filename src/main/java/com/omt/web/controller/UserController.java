package com.omt.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.User;
import com.omt.service.UserNotificationService;
import com.omt.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	 UserService userService;
	 private UserNotificationService userNotificationService;

	    @Autowired
	    public UserController(UserService userService, UserNotificationService userNotificationService) {
	  		this.userService = userService;
	 		this.userNotificationService = userNotificationService;
	  	}


	    @RequestMapping(method = RequestMethod.GET)
	    public List<User> findAll() {
	        return userService.findAll();
	    }

	    @RequestMapping(path = "{id}", method = RequestMethod.GET)
	    public User findOne(@PathVariable("id") Long id) {
	        return userService.findOne(id);
	    }

	    @RequestMapping(method = RequestMethod.POST)
	    public User save(@RequestBody User user) {
	        return userService.save(user);
	    }
	    
	    @RequestMapping(path = "/sentMail", method = RequestMethod.GET)
	    public User findEmail(){
	    User newUser = userService.findOne((long)1);
        userNotificationService.sendNotification(newUser);
		
		return newUser;
	    }
	    
	    @RequestMapping(path = "activate/{activation-code}", method = RequestMethod.GET)
	    	public String activateUser(@PathVariable("activation-code") String code) {
	    		User user = findByCodeForActivation(code);
	    		if(user != null) {
	    			user.setActive(true);
	    			user.setCodeForActivation(null);
	    			userService.save(user);
	    			
	    //			return "redirect:" + "http://localhost:8080/";
	    		}
	    		return "<script>window.location = 'http://localhost:8080/?success=true';</script>";
	     	}

	    @RequestMapping(method = RequestMethod.PUT)
	    public User update(@RequestBody User user) {
	        return userService.save(user);
	    }

	    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable("id") Long id) {
	        userService.delete(id);
	    }
	
	    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	    	public User findByCodeForActivation(@PathVariable("code") String code) {
	    		return userService.findByCodeForActivation(code);
	     	}
}
