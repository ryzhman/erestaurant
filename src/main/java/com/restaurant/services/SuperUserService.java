package com.restaurant.services;

import java.util.List;

import com.restaurant.entities.User;

public interface SuperUserService {
	public User login(String login, String password);
	
	public void saveUser(User newUser);
	
	public User findUser(int id);

	public List<User> findAll();
}
