package com.restaurant.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.restaurant.DAO.LoginDAO;
import com.restaurant.DAO.ReportsDAO;
import com.restaurant.entities.Report;
import com.restaurant.entities.User;

@Named
public class AnalystServiceImpl implements AnalystService{
	@Inject
	private ReportsDAO reportsDAO;
	@Inject 
	private LoginDAO loginDAO;
	
	@Override
	public User login(String login, String password) {
		return loginDAO.login(login, password);
	}

	@Override
	public List<Report> getReportByCountAndSum(Date startDate, Date endDate){
		return reportsDAO.getReportByCountAndSum(startDate, endDate);
	}
	
	@Override
	public List<Report> getReportByMenuItemCatogories(Date startDate, Date endDate){
		return reportsDAO.getReportByMenuItemCatogories(startDate, endDate);
	}
}
