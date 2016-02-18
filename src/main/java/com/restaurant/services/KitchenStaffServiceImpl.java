package com.restaurant.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.restaurant.DAO.OrderItemsDAO;
import com.restaurant.DAO.LoginDAO;
import com.restaurant.DAO.OrderDAO;
import com.restaurant.entities.OrderItems;
import com.restaurant.entities.User;
import com.restaurant.entities.Order;
import com.restaurant.entities.Order.StatusOfDelivery;

@Named
public class KitchenStaffServiceImpl implements KitchenStaffService {

	@Inject
	private OrderItemsDAO itemDAO;
	@Inject
	private OrderDAO orderDAO;
	@Inject
	private LoginDAO loginDAO;
	
	@Override
	public User login(String login, String password){
		return loginDAO.login(login, password);
	}
	
	@Override
	public List<OrderItems> viewItems() {
		return itemDAO.viewItems();
	}
	
	@Override
	public OrderItems findById(int id){
		return itemDAO.findById(id);
	}
	
	@Override
	@Transactional
	public void processItem(OrderItems item) {
//		int i=item.getQuantity(); in interface
//		while(i>0){
//			try {
//				Thread.sleep(1000);
//				--i;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		item.setIsCooked(true);
		itemDAO.saveItem(item);
		System.out.println(item.getId());
		
		List<OrderItems> list = item.getOrder().getListOfDishes();
		if(list.stream().allMatch(t -> t.getIsCooked()==true)){
			Order ord = item.getOrder();
			ord.setStatus(StatusOfDelivery.READY_FOR_SHIPMENT);
			orderDAO.saveOrder(ord);
		}
	}
}
