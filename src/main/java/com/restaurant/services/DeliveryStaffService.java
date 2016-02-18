package com.restaurant.services;

import java.util.List;

import com.restaurant.entities.Order;
import com.restaurant.entities.User;

public interface DeliveryStaffService {
	public User login(String login, String password);

	public List<Order> getListOfOrdersByStatus();
	
	public void shipOrder(Order order);
	
	public void markAsDelivered(Order order);
	
	public Order findById(int id);
}
