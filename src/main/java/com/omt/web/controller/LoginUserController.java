package com.omt.web.controller;

import java.security.MessageDigest;
import java.util.*;

import com.omt.domain.AuthenticatedUser;
import com.omt.domain.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.service.UserNotificationService;
import com.omt.service.UserService;

import javax.mail.MessagingException;


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

	@RequestMapping("/user")
	public AuthenticatedUser getUser(Authentication authentication) {
		List<String> roles = new ArrayList<>();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			roles.add(authority.getAuthority());
		}
		AuthenticatedUser user = new AuthenticatedUser(authentication.getName(), roles);
		return user;
	}

	@RequestMapping(value = "/request-new-password/{email}/", method = RequestMethod.POST)
	public void requestNewPassword(@PathVariable("email") String email) throws MessagingException {
		// Check if user exists and grab an user
		LoginUser user = userService.findByEmail(email);

		if(user != null) {
			// Generate new password
			char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			Random random = new Random();

			for (int i = 0; i < 8; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb.append(c);
			}

			for(int i = 0; i < 60; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb2.append(c);
			}

			String password = sb.toString();
			String password_activation_link = sb2.toString();

			// Save as temp password
			user.setPasswordTemp(password);
			user.setPasswordActivationLink(password_activation_link);
			userService.save(user);

			// Send email with new password
			userNotificationService.sendNewPassword(email, password, password_activation_link);
		}
	}

//	@RequestMapping(value = "/activate-new-password/{password_activation_link}", method = RequestMethod.GET)
//	public void activateNewPassword(@PathVariable("password_activation_link") String password_activation_link) {
//		System.out.println(password_activation_link);
//	}

	@RequestMapping(method = RequestMethod.GET)
	public List<LoginUser> findAll() {
		return userService.findAll();
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public LoginUser findOne(@PathVariable("id") Long id) {
		return userService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public LoginUser save(@RequestBody LoginUser user) throws Exception {

		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 60; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		String token = sb.toString();

		user.setCodeForActivation(token);
		user.setActive(false);
		user.setStatus(true);
		user.setSubscription(true);

		String original = user.getEmail();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuffer hash = new StringBuffer();
		for (byte b : digest) {
			hash.append(String.format("%02x", b & 0xff));
		}

		user.setHashed_email(hash.toString());
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());

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
