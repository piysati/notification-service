package com.project.service;

import com.project.dto.NotificationDTO;
import com.project.exception.BaseException;
import com.project.model.Notification;

import java.util.List;

public interface INotificationService {

	public void notify(String userId, NotificationDTO notification) throws BaseException;

	public List<Notification> getNotifications(String userId) throws BaseException;
}