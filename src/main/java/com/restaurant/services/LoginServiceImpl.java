package com.restaurant.services;

import javax.inject.Inject;
import javax.inject.Named;

import com.restaurant.DAO.LoginDAO;
import com.restaurant.entities.User;
@Named
public class LoginServiceImpl implements LoginService{
	@Inject
	private LoginDAO loginDAO;
	
	@Override
	public User login(String login, String password) {
		return loginDAO.login(login, password);
	}
}
