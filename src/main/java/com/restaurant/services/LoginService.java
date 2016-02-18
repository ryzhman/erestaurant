package com.restaurant.services;

import com.restaurant.entities.User;

public interface LoginService {
	public User login(String login, String password);
}
