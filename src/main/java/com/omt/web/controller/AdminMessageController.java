package com.omt.web.controller;

import java.util.List;

import com.omt.domain.LoginUser;
import com.omt.domain.Role;
import com.omt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.AdminMessage;
import com.omt.service.AdminMessageService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/admin-messages")
public class AdminMessageController {

    AdminMessageService adminMessageService;
    UserService userService;
    JavaMailSender javaMailSender;

    @Autowired
    public AdminMessageController(AdminMessageService adminMessageService,  UserService userService, JavaMailSender javaMailSender) {
        this.adminMessageService = adminMessageService;
        this.userService = userService;
        this.javaMailSender = javaMailSender;
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
        AdminMessage amMessage = adminMessageService.save(adminMessage);

        Role role = new Role();
        role.setType(Role.RoleType.ROLE_ADMIN);
        role.setId((long) 2);

        List<LoginUser> admins = userService.findByRoles(role);
        for (LoginUser admin: admins) {
            try {
                sendEmailToAdmin(admin.getEmail(), adminMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return amMessage;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public AdminMessage update(@RequestBody AdminMessage adminMessage) {
        return adminMessageService.save(adminMessage);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        adminMessageService.delete(id);
    }

    public void sendEmailToAdmin(String email, AdminMessage adminMessage) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        String emailContent = "Somebody complained about this comment\n" + adminMessage.getComment().getCommentContent()
                + "\n" + "on this watchlist " + adminMessage.getWatchlist().getVideo().getName() + " by " + adminMessage.getWatchlist().getWatchlistUser()
                + " they said "+ adminMessage.getMessage();

        helper = new MimeMessageHelper(message, true);
        helper.setFrom("admin_messages@onlinemovietracker.com");
        helper.setTo(email);
        helper.setSubject("Admin message");
        helper.setText(emailContent, true);

        javaMailSender.send(message);
        System.out.println("AdminEmail - Did it");
    }

}
