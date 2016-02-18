package com.restaurant.services;

import java.util.Date;
import java.util.List;

import com.restaurant.entities.Report;
import com.restaurant.entities.User;


public interface AnalystService {
	public User login(String login, String password);
	
	public List<Report> getReportByCountAndSum(Date startDate, Date endDate);
	
	public List<Report> getReportByMenuItemCatogories(Date startDate, Date endDate);
}
