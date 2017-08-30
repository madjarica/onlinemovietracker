package com.omt.service;

import java.util.List;

import com.omt.domain.LoginUser;
import com.omt.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.repository.LoginUserRepository;

@Service
public class UserService {

	LoginUserRepository loginUserRepository;
	
    @Autowired
    public UserService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    public List<LoginUser> findAll() {
        // TODO Auto-generated method stub
        return loginUserRepository.findAll();
    }

    public LoginUser save(LoginUser user) {
        // TODO Auto-generated method stub
        return loginUserRepository.save(user);
    }

    public LoginUser findOne(Long id) {
        // TODO Auto-generated method stub
        return loginUserRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        loginUserRepository.delete(id);
    }
    
    public LoginUser findByCodeForActivation(String code) {
        return loginUserRepository.findByCodeForActivation(code);
    }

    public LoginUser findByUsername(String username) {
        return loginUserRepository.findByUsername(username);
    }

    public LoginUser findByEmail(String email) {
        return loginUserRepository.findByEmail(email);
    }

    public LoginUser findByPasswordActivationLink(String code) {
        return loginUserRepository.findByPasswordActivationLink(code);
    }

    public List<LoginUser> findByRoles(Role role){
        return loginUserRepository.findByRoles(role);
    }
	
}
