//package com.omt.config;
//
//import java.util.HashSet;
//import java.util.Set;
//import javax.transaction.Transactional;
//
//import com.omt.domain.LoginUser;
//import com.omt.domain.Role;
//import com.omt.repository.LoginUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Transactional
//@Service
//public class LoginUserService implements UserDetailsService {
//
//	private User currentUser;
//	private LoginUserRepository loginUserRepository;
//
//	@Autowired
//	public LoginUserService(LoginUserRepository loginUserRepository) {
//		this.loginUserRepository = loginUserRepository;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		try {
//			LoginUser loginUser = loginUserRepository.findByUsername(username);
//
//			if (loginUser == null) {
//				return null;
//			}
//
//			currentUser =  new User(loginUser.getUsername(), loginUser.getPassword(), getAuthorities(loginUser));
//
//			return currentUser;
//
//		} catch (Exception e) {
//			throw new UsernameNotFoundException("User not found");
//		}
//	}
//
//	private Set<GrantedAuthority> getAuthorities(LoginUser loginUser){
//		Set<GrantedAuthority> authorities = new HashSet<>();
//
//		for(Role role : loginUser.getRoles()) {
//			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getType().toString());
//			authorities.add(grantedAuthority);
//		}
//		return authorities;
//	}
//
//	public User getCurrentllyLoggedUser(){
//		return currentUser;
//	}
//
//
//}
