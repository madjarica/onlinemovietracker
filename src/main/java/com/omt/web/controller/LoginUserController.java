package com.omt.web.controller;

import java.util.List;

import com.omt.domain.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.service.UserNotificationService;
import com.omt.service.UserService;


@RestController
@RequestMapping("/users")
public class LoginUserController {

	private Logger logger = LoggerFactory.getLogger(LoginUserController.class);

	UserService userService;
	private UserNotificationService userNotificationService;

	@Autowired
	public LoginUserController(UserService userService, UserNotificationService userNotificationService) {
		this.userService = userService;
		this.userNotificationService = userNotificationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<LoginUser> findAll() {
		return userService.findAll();
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public LoginUser findOne(@PathVariable("id") Long id) {
		return userService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public LoginUser save(@RequestBody LoginUser user) {
		return userService.save(user);
	}

	@RequestMapping(path = "/sentMail", method = RequestMethod.GET)
	public LoginUser findEmail() {
		LoginUser newUser = userService.findOne((long)1);
		userNotificationService.sendNotification(newUser);

		return newUser;
	}

	@RequestMapping(path = "activate/{activation-code}", method = RequestMethod.GET)
	public String activateUser(@PathVariable("activation-code") String code) {
	LoginUser loginUser = findByCodeForActivation(code);
		if(loginUser != null) {
			loginUser.setActive(true);
			loginUser.setCodeForActivation(null);
			userService.save(loginUser);

//			return "redirect:" + "http://localhost:8080/";
		}
		return "<script>window.location = 'http://localhost:8080/?success=true';</script>";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public LoginUser update(@RequestBody LoginUser user) {
		return userService.save(user);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}

	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public LoginUser findByCodeForActivation(@PathVariable("code") String code) {
		return userService.findByCodeForActivation(code);
	}

	@RequestMapping(path = "username/{username}", method = RequestMethod.GET)
	public LoginUser findByUsername(@PathVariable("username") String username) {
		return userService.findByUsername(username);
	}
}
