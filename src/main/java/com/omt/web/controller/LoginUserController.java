package com.omt.web.controller;

import java.net.URI;
import java.security.MessageDigest;
import java.util.*;

import com.omt.JsonResults.HashedEmail;
import com.omt.JsonResults.Password;
import com.omt.JsonResults.UpdateUser;
import com.omt.domain.AuthenticatedUser;
import com.omt.domain.LoginUser;
import com.omt.domain.Role;

import com.omt.domain.WatchlistCollection;
import com.omt.service.WatchlistCollectionService;
import com.omt.startup.OmtScheduleStartup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.omt.service.UserNotificationService;
import com.omt.service.UserService;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/users")
public class LoginUserController {

	UserService userService;
	private UserNotificationService userNotificationService;
	private WatchlistCollectionService watchlistCollectionService;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestOperations restTemplate;

	@Autowired
	public LoginUserController(UserService userService, UserNotificationService userNotificationService, WatchlistCollectionService watchlistCollectionService) {
		this.userService = userService;
		this.userNotificationService = userNotificationService;
		this.watchlistCollectionService = watchlistCollectionService;
	}

	// Find single user by ID
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public LoginUser findOne(@PathVariable("id") Long id) {
		return userService.findOne(id);
	}

	// Find secured user by ID
	@RequestMapping(path = "secure/{id}", method = RequestMethod.GET)
	public LoginUser findOneSecure(@PathVariable("id") Long id) {
		LoginUser user = userService.findOne(id);
		user.setPassword(null);
		user.setPasswordTemp(null);
		user.setPasswordActivationLink(null);
		user.setCodeForActivation(null);
		user.setHashed_email(null);
		return user;
	}

	// Function for finding user by activation code
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public LoginUser findByCodeForActivation(@PathVariable("code") String code) {
		return userService.findByCodeForActivation(code);
	}

	// Function for finding user by username
	@RequestMapping(path = "username/{username}", method = RequestMethod.GET)
	public LoginUser findByUsername(@PathVariable("username") String username) {
		return userService.findByUsername(username);
	}

	// Function for getting all users
	@RequestMapping(method = RequestMethod.GET)
	public List<LoginUser> findAll() {
		List<LoginUser> users = userService.findAll();

		for(LoginUser user : users) {
			user.setPassword(null);
			user.setPasswordTemp(null);
			user.setPasswordActivationLink(null);
			user.setCodeForActivation(null);
			user.setHashed_email(null);
		}

		return users;
	}

	// Function for updating user
	@RequestMapping(method = RequestMethod.PUT)
	public LoginUser update(@RequestBody LoginUser user) {
		return userService.save(user);
	}

	// Function for deleting user
	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}

	// Function for changing password
	@RequestMapping(value = "/change-password/{username}", method = RequestMethod.POST)
	public void changePassword(@PathVariable("username") String username, @RequestBody Password password) throws Exception {
		LoginUser user = userService.findByUsername(username);

		if(user != null) {
			//exception for short password
			if(user.getPassword().length() <= 6) {
				throw new Exception("Your password is too short.");
			}

			if(user.getPassword().equals(password.getOldPassword())) {
				user.setPassword(password.getNewPassword());
				userService.save(user);
			}
		}
	}

	// Function for getting hashed email needed for gravatar service
	@RequestMapping(value = "/hashed-email/{username}", method = RequestMethod.GET)
	public HashedEmail getHashedEmail(@PathVariable("username") String username) {
		LoginUser user = userService.findByUsername(username);
		HashedEmail hashedEmail = new HashedEmail();

		if(user != null) {
			String hash = user.getHashed_email();
			hashedEmail.setHashedEmail(hash);
			return hashedEmail;
		}

		hashedEmail.setHashedEmail("?d=identicon");
		return hashedEmail;
	}

	// Function for logging in user
	@RequestMapping("/user")
	public AuthenticatedUser getUser(Authentication authentication) throws Exception {

		LoginUser loginUser = userService.findByUsername(authentication.getName());

		// Logic for checking is user active and not blocked
		if(loginUser != null) {
			if(!loginUser.isActive()) { // User is not active
				// exception da nalog nije aktiviran
				throw new Exception("Your account is not active.");
			}

			if(loginUser.isStatus()) { // User is blocked
				// exception da je user blokiran
				throw new Exception("Your account is blocked by Admin.");
			}

			Date current_date = new Date();

			if(loginUser.getBlockedUntil() != null) {
				if(loginUser.getBlockedUntil().after(current_date)) { // User is blocked until
					// exception da je user blokiran do tad i tad
					throw new Exception("Your account is blocked until " + loginUser.getBlockedUntil());
				}
			}
		}

		List<String> roles = new ArrayList<>();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			roles.add(authority.getAuthority());
		}
		AuthenticatedUser user = new AuthenticatedUser(authentication.getName(), roles);
		return user;
	}

	// Function for requesting new password if user forgotten password
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

	// Function for activating new requested password
	@RequestMapping(value = "/activate-new-password/{password_activation_code}", method = RequestMethod.GET)
	public String activateNewPassword(@PathVariable("password_activation_code") String password_activation_code) {

		LoginUser user = userService.findByPasswordActivationLink(password_activation_code);

		if(user != null) {
			user.setPassword(user.getPasswordTemp());
			user.setPasswordActivationLink(null);
			user.setPasswordTemp(null);
			userService.save(user);

			return "<script>window.location = '" + OmtScheduleStartup.home + "messages/success-password-activation';</script>";
		}

		return "<script>window.location = '" + OmtScheduleStartup.home + "messages/failed-password-activation';</script>";
	}

	// Function for user registration
	@RequestMapping(method = RequestMethod.POST)
	public LoginUser save(@RequestBody LoginUser user) throws Exception {

		// exception for used username
		LoginUser userCheckUsername = userService.findByUsername(user.getUsername());
		if(userCheckUsername != null) {
			throw new Exception("Username already taken");
		}

		// exception for user email
		LoginUser userCheckEmail = userService.findByEmail(user.getEmail());
		if(userCheckEmail != null) {
			throw new Exception("Email already taken");
		}

		// exception for short password
		LoginUser userCheckPassword = userService.findByEmail(user.getEmail());
		if(userCheckPassword != null) {
			if(userCheckPassword.getPassword().length() <= 6) {
				throw new Exception("Your password is too short.");
			}
		}

		//Create user watchlist collection
		WatchlistCollection watchlistCollection = new WatchlistCollection();
		watchlistCollection.setUsername(user.getUsername());
		watchlistCollectionService.save(watchlistCollection);

		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 60; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		String account_activation_link = sb.toString();

		user.setCodeForActivation(account_activation_link);
		user.setActive(false);
		user.setStatus(false);
		user.setSubscription(true);

		Set<Role> role = new HashSet<Role>();

		Role newRole = new Role();
		newRole.setId((long)1);
		newRole.setType(Role.RoleType.ROLE_USER);
		role.add(newRole);

		user.setRoles(role);

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

		userNotificationService.sendActivationLink(user.getEmail(), account_activation_link);

		return userService.save(user);
	}

	// Function for activating user
	@RequestMapping(path = "activate/{activation-code}", method = RequestMethod.GET)
	public String activateUser(@PathVariable("activation-code") String code) {
	LoginUser user = findByCodeForActivation(code);
		if(user != null) {
			user.setActive(true);
			user.setCodeForActivation(null);
			userService.save(user);

			return "<script>window.location = '" + OmtScheduleStartup.home + "messages/success-account-activation';</script>";
		}
		return "<script>window.location = '" + OmtScheduleStartup.home + "messages/failed-account-activation';</script>";
	}

	// Admin function for updating user information about status, active, subscription and Date until user is blocked
	// TODO Exceptions
	@RequestMapping(path = "update-user/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable("id") Long id, @RequestBody UpdateUser userData) throws Exception {

		//Grab an user
		LoginUser user = userService.findOne(id);

		// Update fields
		if(user != null) {
			user.setSubscription(userData.isSubscription());
			user.setActive(userData.isActive());
			user.setStatus(userData.isStatus());
			user.setBlockedUntil(userData.getBlockedUntil());

			boolean checkRole = userData.isRole();

			Set<Role> role = new HashSet<Role>();
			user.setRoles(role);

			if(checkRole) { // admin je
				Role userRole = new Role();
				Role adminRole = new Role();
				userRole.setId((long)1);
				userRole.setType(Role.RoleType.ROLE_USER);
				adminRole.setId((long)2);
				adminRole.setType(Role.RoleType.ROLE_ADMIN);
				role.add(userRole);
				role.add(adminRole);
				user.setRoles(role);
			} else {
				Role userRole = new Role();
				userRole.setId((long)1);
				userRole.setType(Role.RoleType.ROLE_USER);
				role.add(userRole);
				user.setRoles(role);
			}

			Date current_date = new Date();

			user.setUpdatedDate(current_date);
			userService.save(user);
		} else {
			throw new Exception("This user not exists");
		}
	}

	@RequestMapping(path ="/captcha", method = RequestMethod.POST)
	public String getSomethingSomething(@RequestBody Map<String, String> request){
		String response = request.get("g-recaptcha-response");
		String secret = "6Ld4Ii4UAAAAAC2-gF8dnqW9zzFm1YQxLuxDmedv";
		URI verifyUri = URI.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", secret, response));
		String googleResponse = restTemplate.getForObject(verifyUri, String.class);
		return googleResponse;
	}
}
