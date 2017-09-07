package com.omt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig<MovieUserService> extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginUserService loginUserService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/", "/bower_components/**", "/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll()
			.antMatchers("/index.html", "/views/home.html", "/views/movie-details.html", "/views/tv-show-details.html", "/views/search-results.html", "/views/add-episode.html").permitAll()
			.antMatchers("/includes/_footer.html", "/includes/_search-input.html", "/includes/_top-navigation.html").permitAll()
			.antMatchers("/messages/failed-account-activation.html", "/messages/failed-password-activation.html", "/messages/success-account-activation.html", "/messages/success-password-activation.html", "/messages/success-password-change.html").permitAll()
			.antMatchers("/fonts/**", "/font/**").permitAll()
			.antMatchers("https://image.tmdb.org/**", "http://image.tmdb.org/**").permitAll()
			.antMatchers("/views/admin/**").permitAll()
			.antMatchers("/views/user/**").permitAll()
			.antMatchers(HttpMethod.GET, "/users").permitAll()
			.antMatchers(HttpMethod.GET,"/users/code/*").permitAll()
			.antMatchers(HttpMethod.GET,"/users/activate/*").permitAll()
			.antMatchers(HttpMethod.GET,"/tvshows").permitAll()
			.antMatchers(HttpMethod.GET,"/tvshows/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/movies").permitAll()
			.antMatchers(HttpMethod.GET,"/movies/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/videos").permitAll()
			.antMatchers(HttpMethod.GET,"/videos/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/videos/search/video/{title}").permitAll()
			.antMatchers(HttpMethod.POST,"/users/captcha").permitAll()
			.antMatchers(HttpMethod.POST,"/users/request-new-password/**").permitAll()
			.antMatchers(HttpMethod.GET,"/users/activate-new-password/**").permitAll()
			.antMatchers(HttpMethod.POST,"/users").permitAll()
			.antMatchers(HttpMethod.GET,"/watchlists/get-ratings/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/ratings").permitAll()
			.anyRequest().fullyAuthenticated().and()
			.httpBasic().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable();
	}
}