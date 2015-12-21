package com.restaurant.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="DishesList")
public class OrderItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="dish_id")
	private Dish dish;
	private int quantity;
	private double totalPrice;
	private boolean isCooked;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	public OrderItems(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		totalPrice = quantity*dish.getPrice();
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public boolean getIsCooked() {
		return isCooked;
	}
	public void setIsCooked(boolean isCooked) {
		this.isCooked = isCooked;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Item id - " + id + ", dish - " + dish.getName() + ", quantity - " + quantity + ", totalPrice - " + totalPrice
				+ ", isCooked - " + isCooked + ", order id - " + order.getId() + ", time of order - " + order.getTimeOfOrder();
	}
	
}
