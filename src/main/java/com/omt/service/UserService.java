package com.omt.service;

import com.omt.domain.OmtUser;
import com.omt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<OmtUser> findAll() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    public OmtUser save(OmtUser user) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    public OmtUser findOne(Long id) {
        // TODO Auto-generated method stub
        return userRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        userRepository.delete(id);
    }
}
