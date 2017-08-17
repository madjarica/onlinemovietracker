package com.omt.service;

import com.omt.domain.LoginUser;
import com.omt.domain.UserNotification;
import com.omt.repository.UserNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class UserNotificationService {

    UserNotificationRepository userNotificationRepository;

    private JavaMailSender javaMailSender;
 	
 	@Autowired
 	public UserNotificationService(JavaMailSender javaMailSender, UserNotificationRepository userNotificationRepository) {
 		this.javaMailSender = javaMailSender;
 		this.userNotificationRepository = userNotificationRepository;
 	}

    public List<UserNotification> findAll() {
        // TODO Auto-generated method stub
        return userNotificationRepository.findAll();
    }

    public UserNotification save(UserNotification userNotification) {
        // TODO Auto-generated method stub
        return userNotificationRepository.save(userNotification);
    }

    public UserNotification findOne(Long id) {
        // TODO Auto-generated method stub
        return userNotificationRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        userNotificationRepository.delete(id);
    }

    public void sendNewPassword(String email, String password, String password_activation_link) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String emailContent = "You requested new password.<br><br>New password: " + password + "<br><br>Before you can use it, you'll need first to activate it.<br><br><a href='http://localhost:8080/users/activate-new-password/" + password_activation_link + "'>Activate new password</a><br><br>If you did'nt requested new password, just ignore this email.";

		helper = new MimeMessageHelper(message, true);
		helper.setFrom("notification@onlinemovietracker.com");
		helper.setTo(email);
		helper.setSubject("New Password");
		helper.setText(emailContent, true);

		javaMailSender.send(message);

	}
    
    public void sendNotification(LoginUser loginUser) throws MailException {
 		
 		String emailContent = "Thank you for registration. In order to use your account, you'll need to activate it. Just click activate to confirm.<br><br>Activation code: <a href='http://localhost:8080/users/activate/" + loginUser.getCodeForActivation() + "'>Activate your account</a>";
 		
 		//send email		
 		SimpleMailMessage mail = new SimpleMailMessage();
 		mail.setTo(loginUser.getEmail());
 		mail.setFrom("notification@onlinemovietracker.com");
 		mail.setSubject("Registration email");
 		mail.setText(emailContent);
 		
 		javaMailSender.send(mail);
 	}

}
