package com.restaurant.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.restaurant.DAO.DishDAO;
import com.restaurant.DAO.LoginDAO;
import com.restaurant.DAO.OrderDAO;
import com.restaurant.DAO.OrderItemsDAO;
import com.restaurant.entities.Dish;
import com.restaurant.entities.Order;
import com.restaurant.entities.Order.StatusOfDelivery;
import com.restaurant.entities.OrderItems;
import com.restaurant.entities.User;

@Named
public class CustomerServiceImpl implements CustomerService {
	@Inject
	private LoginDAO loginDAO;
	@Inject
	private OrderDAO orderDAO;
	@Inject
	private DishDAO dishDAO;
	@Inject
	private OrderItemsDAO ordItemDAO;
	
	@Override
	public User login(String login, String password) {
		return loginDAO.login(login, password);
	}
	
	@Transactional
	@Override
	public void saveOrder(Order order) {
		if(order.getUser().getIsActive()!=false){
			order.setStatus(StatusOfDelivery.PENDING);
			order.setTimeOfOrder(Timestamp.valueOf(LocalDateTime.now()));
			List<OrderItems> list = order.getListOfDishes();
			for(OrderItems e: list){
				if(e.getDish().getIsKitchenMade()==false)
					e.setIsCooked(true);
			}
			orderDAO.saveOrder(order);
		}
	}
	
	@Override
	public List<Order> findByCustomer(int id){
		return orderDAO.findByCustomer(id);
	}

	@Override
	public Dish findDishById(int id) {
		return dishDAO.findById(id);
	}
	
	@Override
	public List<OrderItems> findAll(){
		return ordItemDAO.findAll();
	}
	
	@Override
	public List<Dish> findAllDishes(){
		return dishDAO.findAll();
	}
	
	
}
