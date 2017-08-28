package com.omt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.AdminMessage;
import com.omt.service.AdminMessageService;

@RestController
@RequestMapping("/admin-messages")
public class AdminMessageController {

    AdminMessageService adminMessageService;

    @Autowired
    public AdminMessageController(AdminMessageService adminMessageService) {
        this.adminMessageService = adminMessageService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<AdminMessage> findAll() {
        return adminMessageService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public AdminMessage findOne(@PathVariable("id") Long id) {
        return adminMessageService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AdminMessage save(@RequestBody AdminMessage adminMessage) {
        return adminMessageService.save(adminMessage);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public AdminMessage update(@RequestBody AdminMessage adminMessage) {
        return adminMessageService.save(adminMessage);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        adminMessageService.delete(id);
    }

}
