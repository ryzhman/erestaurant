package com.restaurant.DAO;

import com.restaurant.entities.User;

public interface LoginDAO {
	public User login(String login, String password);
}
