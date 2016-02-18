package com.restaurant.DAO;

import java.util.Collection;
import java.util.List;

import com.restaurant.entities.OrderItems;

public interface OrderItemsDAO {
	public List<OrderItems> viewItems();
	
//	public void updateItem(Item itemToMark);
	
	public Collection<OrderItems> filterItemByStatus();
	
	public void saveItem(OrderItems item);
	
	public OrderItems findById(int id);
	
	public List<OrderItems> findAll();
}
