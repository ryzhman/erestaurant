package com.restaurant.DAO;

import java.util.List;

import com.restaurant.entities.User;
public interface UserDAO {
	
	public void saveUser(User newUser);
	
	public User findById(int id);
	
	public List<User> findAll();
}
