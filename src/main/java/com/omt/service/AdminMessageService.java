package com.omt.service;

import com.omt.domain.AdminMessage;
import com.omt.repository.AdminMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMessageService {

    AdminMessageRepository adminMessageRepository;

    @Autowired
    public AdminMessageService(AdminMessageRepository adminMessageRepository) {
        this.adminMessageRepository = adminMessageRepository;
    }

    public List<AdminMessage> findAll() {
        // TODO Auto-generated method stub
        return adminMessageRepository.findAll();
    }

    public AdminMessage save(AdminMessage adminMessage) {
        // TODO Auto-generated method stub
        return adminMessageRepository.save(adminMessage);
    }

    public AdminMessage findOne(Long id) {
        // TODO Auto-generated method stub
        return adminMessageRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        adminMessageRepository.delete(id);
    }

    public void deleteForComments(Long id){
        List<AdminMessage> adminMessages = adminMessageRepository.findByCommentId(id);
        for (int i = 0; i < adminMessages.size(); i++) {
            adminMessageRepository.delete(adminMessages.get(i));
        }
    }

    public List<AdminMessage> findByWatchlistId(Long id){
        return adminMessageRepository.findByWatchlistId(id);
    }

}
