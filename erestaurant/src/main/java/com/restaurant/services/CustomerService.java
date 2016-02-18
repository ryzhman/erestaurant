package com.restaurant.services;

import java.util.List;

import com.restaurant.entities.Dish;
import com.restaurant.entities.Order;
import com.restaurant.entities.OrderItems;
import com.restaurant.entities.User;

public interface CustomerService {
	public User login(String login, String password);

	public void saveOrder(Order order);
	
	public Dish findDishById(int id);
	
	public List<OrderItems> findAll();
	
	public List<Order> findByCustomer(int id);
	
	public List<Dish> findAllDishes();
}
