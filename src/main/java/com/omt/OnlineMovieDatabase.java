package com.omt;

import com.omt.config.CustomUserDetails;
import com.omt.domain.Role;
import com.omt.domain.User;
import com.omt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@SpringBootApplication
public class OnlineMovieDatabase {

	public static void main(String[] args) {
        SpringApplication.run(OnlineMovieDatabase.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception{
		if(repo.count() == 0) {
			repo.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ADMIN"))));
		}
		builder.userDetailsService(new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return new CustomUserDetails(repo.findByUsername(username));
			}
		});
	}
}