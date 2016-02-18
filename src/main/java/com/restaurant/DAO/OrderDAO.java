package com.restaurant.DAO;

import java.util.List;

import com.restaurant.entities.Order;

public interface OrderDAO {
	public void saveOrder(Order order);
	
	public Order findById(int id);
	
	public List<Order> getListOfOrders();
	
	public List<Order> findByCustomer(int id);
}
