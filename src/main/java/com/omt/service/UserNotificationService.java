package com.omt.service;

import com.omt.domain.User;
import com.omt.domain.UserNotification;
import com.omt.repository.UserNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
    
    public void sendNotification(User user) throws MailException {
 		
 		String emailContent = "Thank you for registration. In order to use your account, you'll need to activate it. Just click activate to confirm.<br><br>Activation code: <a href='http://localhost:8080/users/activate/" + user.getCodeForActivation() + "'>Activate your account</a>";
 		
 		//send email		
 		SimpleMailMessage mail = new SimpleMailMessage();
 		mail.setTo(user.getEmail());
 		mail.setFrom("notification@onlinemovietracker.com");
 		mail.setSubject("Registration email");
 		mail.setText(emailContent);
 		
 		javaMailSender.send(mail);
 	}

}
