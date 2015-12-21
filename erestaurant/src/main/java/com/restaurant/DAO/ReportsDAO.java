package com.restaurant.DAO;

import java.util.Date;
import java.util.List;

import com.restaurant.entities.Report;

public interface ReportsDAO {
	public List<Report> getReportByCountAndSum(Date startDate, Date endDate);
	
	public List<Report> getReportByMenuItemCatogories(Date startDate, Date endDate);
}
