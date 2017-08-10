package com.omt.config;

import com.omt.domain.OmtUser;
import com.omt.domain.Role;
import com.omt.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class OmtUserService implements UserDetailsService{

    private User currentUser;
    private UserRepository userRepository;

    public OmtUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            OmtUser user = userRepository.findByUsername(username);
            if (user == null) {
                return null;
            }
            currentUser = new User(user.getUsername(), user.getPassword(), getAuthorities(user));
            return currentUser;
        }catch (Exception e){
            throw new UsernameNotFoundException("Username not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(OmtUser user){
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getType());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}
