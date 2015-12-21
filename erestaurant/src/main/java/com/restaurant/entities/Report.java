package com.restaurant.entities;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Report {
	@Temporal(TemporalType.DATE)
	private Date date;
	private Long count;
	private String nameOfCategory;
	private double ordersSum;
	
	public Report(){}
	public Report(Date date, double sum, Long count){
		this.date = date;
		this.count = count;
		this.ordersSum = sum;
	}
	public Report(String name, Long count, double sum){
//		this.date = date;
		this.count = count;
		this.nameOfCategory = name;
		this.ordersSum = sum;
	}
	
//	public Report(int day, int month, int year, String name, long count, double sum){
//		this.date = LocalDate.of(year, month, day);
//		this.count = count;
//		this.nameOfCategory = name;
//		this.ordersSum = sum;
//	}
//	public Report(int day, int month, int year, long ordersCount, double ordersSum){
//		this.date = LocalDate.of(year, month, day);
//		this.count = ordersCount;
//		this.ordersSum = ordersSum;
//	}
	
	public double getOrdersSum() {
		return ordersSum;
	}
	public void setOrdersSum(double ordersSum) {
		this.ordersSum = ordersSum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getNameOfCategory() {
		return nameOfCategory;
	}
	public void setNameOfCategory(String nameOfCategory) {
		this.nameOfCategory = nameOfCategory;
	}
	@Override
	public String toString() {
		return "Report date - " + date + ", count - " + count + ", nameOfCategory - " + nameOfCategory
				+ ", ordersSum - " + ordersSum;
	}
}
