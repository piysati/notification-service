package com.project.service;

import com.project.dto.NotificationDTO;
import com.project.exception.*;
import com.project.model.Notification;
import com.project.model.User;
import com.project.repository.INofiticationRepository;
import com.project.utility.service.EmailService;
import com.project.utility.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private INofiticationRepository notifRepo;

	@Autowired
	private SmsService smsService;

	@Override
	public void notify(String userId, NotificationDTO notification) throws BaseException {
		User user = userService.getUser(userId);
		if (user != null) {
			createNotification(notification, user);
			sendNotification(user, notification);
		} else {
			LOG.error("User not found in database");
			throw new UserNotFoundException("User not found in database");
		}
	}

	private void createNotification(NotificationDTO notification, User user) throws BaseException {
		try {
			Notification notif = new Notification();
			notif.setSubject(notification.getSubject());
			notif.setMessage(notification.getMessage());
			notif.setUser(user);
			notifRepo.save(notif);
		} catch (Exception ex) {
			throw new NotificationException(ex.getMessage());
		}
	}

	private void sendNotification(User user, NotificationDTO notification)
			throws BaseException {
		sendEmail(user, notification);
		sendSMS(user, notification);
	}


	private void sendSMS(User user, NotificationDTO notification) throws SmsException {
		smsService.sendSMS(user, notification);
	}

	private void sendEmail(User user, NotificationDTO notification) throws EmailException {
		emailService.sendEmail(user, notification);
	}

	@Override
	public List<Notification> getNotifications(String userId) throws NotificationException {
		try {
			return notifRepo.findAllByUserId(Short.valueOf(userId));
		} catch (NumberFormatException ex) {
			throw new NotificationException(ex.getMessage());
		}
	}
}
