package com.project.service;

import com.project.model.User;

import java.util.List;

public interface IUserService {

	User getUser(String userId);

	List<User> getUsers();

}
