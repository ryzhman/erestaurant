package com.restaurant.serviceBeans;

import static java.lang.Math.toIntExact;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.restaurant.entities.Order;
import com.restaurant.entities.Order.StatusOfDelivery;
import com.restaurant.entities.OrderItems;
import com.restaurant.services.DeliveryStaffService;
import com.restaurant.services.KitchenStaffService;

@Named
@Scope("session")
public class OrderBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Order> listForDeliv;
	private Order order;
	private List<OrderItems> listForCook;
	private Map<Long, Integer> mapOfChief;
	private OrderItems item;
	private StatusOfDelivery status = StatusOfDelivery.READY_FOR_SHIPMENT;
	private static final Logger log = LogManager.getLogger();
	@Inject
	private UserBean userBean;
	@Inject
	private DeliveryStaffService delStaff;
	@Inject
	private KitchenStaffService kitchStaff;
	
	public OrderBean(){
	}
	
	@PostConstruct
	public void init(){
		order = new Order();
		listForDeliv = delStaff.getListOfOrdersByStatus();
		item = new OrderItems();
		mapOfChief = new HashMap<>();
		listForCook = kitchStaff.viewItems();
		for(OrderItems e: listForCook){
			mapOfChief.put(Long.valueOf(e.getDish().getId()), e.getQuantity());
		}
	}
	
//	Delivery
	public void getListForDelivery(){
		listForDeliv = delStaff.getListOfOrdersByStatus();
	}
	
	public String shipOrder(String id){
//		order = new Order();
		int n = Integer.valueOf(id);
		order = delStaff.findById(n);
		delStaff.shipOrder(order);
		log.info("Delivery responsible " + userBean.getLoginUser().getLogin() + " shipped the order " + order.getId());
		
//		if(status.toString().equals("DELIVERING")){
//			status = StatusOfDelivery.READY_FOR_SHIPMENT;
//		}
//		if(status.toString().equals("DELIVERED")){
//			delStaff.changeStatusOfOrder(order);
//			status = StatusOfDelivery.READY_FOR_SHIPMENT;
//		}
		return "/staffPages/delivery/Delivery-Main";
	}
	
	public String markAsDelivered(String id){
		int n = Integer.valueOf(id);
		order = delStaff.findById(n);
		delStaff.markAsDelivered(order);
		log.info("Delivery responsible " + userBean.getLoginUser().getLogin() + " marked the order " + order.getId() + "as delivered");

//		if(status.toString().equals("DELIVERING"){
//			delStaff.shipOrder(order);
//		}else
//			
//		System.out.println(order.getStatus());
//		order = null;
		return "/staffPages/Delivery-Main";
	}
	
    public void statusChange(ValueChangeEvent event) {
    	status = (StatusOfDelivery)event.getNewValue();
    }

	
//	Kitchen
	public void refreshList(){
		for(OrderItems e: listForCook){
			item = e;
			mapOfChief.put(Long.valueOf(item.getId()), item.getQuantity());
		}
	}
	
	public String cookDish(Long id){
		int quantity = mapOfChief.get(id);
		System.out.println(quantity);
		if(quantity==1){
			for(OrderItems e: listForCook){
				if(id==e.getDish().getId()){
					System.out.println(e);
					kitchStaff.processItem(e);
					mapOfChief.remove(id);
				}
			}
		}else{
			mapOfChief.put(Long.valueOf(id), quantity-1);
		}
		log.info("Chief " + userBean.getLoginUser().getLogin() + " marked the dish " + id + " as cooked");

//		kitchStaff.processItem(item);
		return "/staffPages/kitchen/KitchenStaff-Main";
	}
	
	public String getNameOfDish(Long id){
		for(OrderItems e: listForCook){
			if(e.getDish().getId()==toIntExact(id)){
				return e.getDish().getName();
			}
		}
		return "";
	}

	public List<Order> getListForDeliv() {
		return listForDeliv;
	}

	public void setListForDeliv(List<Order> listForDeliv) {
		this.listForDeliv = listForDeliv;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItems> getListForCook() {
		return listForCook;
	}

	public void setListForCook(List<OrderItems> listForCook) {
		this.listForCook = listForCook;
	}

	public OrderItems getItem() {
		return item;
	}

	public void setItem(OrderItems item) {
		this.item = item;
	}

	public Map<Long, Integer> getMapOfChief() {
		return mapOfChief;
	}

	public void setMapOfChief(Map<Long, Integer> mapOfChief) {
		this.mapOfChief = mapOfChief;
	}

	public StatusOfDelivery getStatus() {
		return status;
	}

	public void setStatus(StatusOfDelivery status) {
		this.status = status;
	}
}
