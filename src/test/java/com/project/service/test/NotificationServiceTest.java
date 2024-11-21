package com.project.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.project.dto.NotificationDTO;
import com.project.exception.BaseException;
import com.project.exception.UserNotFoundException;
import com.project.model.User;
import com.project.repository.INofiticationRepository;
import com.project.service.IUserService;
import com.project.service.NotificationService;
import com.project.utility.service.EmailService;
import com.project.utility.service.SmsService;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

	@Mock
	private IUserService userService;

	@Mock
	private EmailService emailService;

	@Mock
	private INofiticationRepository notifRepo;

	@Mock
	private SmsService smsService;

	@InjectMocks
	private NotificationService service;

	@Test
	public void notifyTest_Success() throws BaseException {
		NotificationDTO notification = new NotificationDTO();
		notification.setMessage("Hi test message");
		String userId = "1";
		User user = new User();
		Mockito.when(userService.getUser(userId)).thenReturn(user);
		service.notify(userId, notification);
	}

	@Test
	public void notifyTest_UserNotFound() throws BaseException {
		NotificationDTO notification = new NotificationDTO();
		String userId = "1";
		service.notify(userId, notification);

		Assertions.assertThrows(UserNotFoundException.class, () -> {
			userService.getUser("1"); // Method that throws UserNotFoundException
		});
	}
}
