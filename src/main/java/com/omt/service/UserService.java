package com.omt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.User;
import com.omt.repository.UserRepository;

@Service
public class UserService {

	UserRepository userRepository;

	
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    public User save(User user) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    public User findOne(Long id) {
        // TODO Auto-generated method stub
        return userRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        userRepository.delete(id);
    }
    
    public User findByCodeForActivation(String code) {
    	 		return userRepository.findByCodeForActivation(code);
    }
	
}
