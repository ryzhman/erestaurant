package com.restaurant.services;

import java.util.List;

import com.restaurant.entities.OrderItems;
import com.restaurant.entities.User;

public interface KitchenStaffService {
	public List<OrderItems> viewItems();
	
	public void processItem(OrderItems item);
	
	public User login(String login, String password);
	
	public OrderItems findById(int id);
}
