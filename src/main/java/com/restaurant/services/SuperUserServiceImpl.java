package com.restaurant.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.restaurant.DAO.LoginDAO;
import com.restaurant.DAO.UserDAO;
import com.restaurant.entities.User;

@Named
public class SuperUserServiceImpl implements SuperUserService {
	@Inject
	private LoginDAO loginDAO;
	@Inject
	private UserDAO userDAO;
	
	@Override
	public User login(String login, String password) {
		return loginDAO.login(login, password);
	}

	@Transactional
	@Override
	public void saveUser(User newUser){
		userDAO.saveUser(newUser);
	}
	
	public User findUser(int id){
		return userDAO.findById(id);
	}
	
	public List<User> findAll(){
		return userDAO.findAll();
	}
}
