package com.omt.web.controller;

import com.omt.domain.OmtUser;
import com.omt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<OmtUser> findAll() {
        return userService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public OmtUser findOne(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public OmtUser save(@RequestBody OmtUser user) {
        return userService.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public OmtUser update(@RequestBody OmtUser user) {
        return userService.save(user);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
