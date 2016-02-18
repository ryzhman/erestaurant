package com.restaurant.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.restaurant.DAO.LoginDAO;
import com.restaurant.DAO.OrderDAO;
import com.restaurant.entities.Order;
import com.restaurant.entities.Order.StatusOfDelivery;
import com.restaurant.entities.User;

@Named
public class DeliveryStaffServiceImpl implements DeliveryStaffService {
	@Inject
	private LoginDAO loginDAO;
	@Inject
	private OrderDAO orderDAO;
	
	@Override
	public User login(String login, String password) {
		return loginDAO.login(login, password);
	}
	@Override
	public List<Order> getListOfOrdersByStatus() {
		return orderDAO.getListOfOrders();
	}
	@Transactional
	@Override
	public void shipOrder(Order order){
		order.setStatus(StatusOfDelivery.DELIVERING);
		orderDAO.saveOrder(order);
	}
	@Transactional
	@Override
	public void markAsDelivered(Order order){
		order.setStatus(StatusOfDelivery.DELIVERED);
		orderDAO.saveOrder(order);
	}
	
	@Override
	public Order findById(int id){
		return orderDAO.findById(id);
	}

}
