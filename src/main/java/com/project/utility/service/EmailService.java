
package com.project.utility.service;

import com.project.dto.NotificationDTO;
import com.project.exception.EmailException;
import com.project.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

	private static final String SENDER_MAIL = "piyushsati4@gmail.com";
	private static final String MAIL_SUBJECT = "Test-Email from Piyush";
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(User user, NotificationDTO notification) throws EmailException {
		LOG.info("Sending mail to user: {}", user.getEmail());
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(SENDER_MAIL);
		mail.setSubject(notification.getSubject());
		mail.setText(notification.getMessage());
		try {
			javaMailSender.send(mail);
			LOG.info("Mail sent...");
		} catch (MailException ex) {
			LOG.error("Mail not sent. Error={}", ex.getMessage());
		} catch(Exception ex) {
			LOG.error("Mail not sent. Error={}", ex.getMessage());
			throw new EmailException(ex.getMessage());
		}
	}

}
