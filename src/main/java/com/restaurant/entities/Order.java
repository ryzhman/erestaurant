package com.restaurant.entities;

import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.PERSIST)
	private List<OrderItems> listOfDishes;
	
	private String address;
	private java.sql.Timestamp timeOfOrder;
	private java.sql.Timestamp timeOfDelivery;
	private double price;
	
	@Convert(converter = OrderStatusConverter.class)
	private StatusOfDelivery status;
	
	public Order(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public java.sql.Timestamp getTimeOfOrder() {
		return timeOfOrder;
	}
	public void setTimeOfOrder(java.sql.Timestamp timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}

	public java.sql.Timestamp getTimeOfDelivery() {
		return timeOfDelivery;
	}
	public void setTimeOfDelivery(java.sql.Timestamp timeOfDelivery) {
		this.timeOfDelivery = timeOfDelivery;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public StatusOfDelivery getStatus() {
		return status;
	}
	public void setStatus(StatusOfDelivery status) {
		this.status = status;
	}

	public List<OrderItems> getListOfDishes() {
		return listOfDishes;
	}

	public void setListOfDishes(List<OrderItems> listOfDishes) {
		this.listOfDishes = listOfDishes;
		/*Eclipselink ignores entity classes with lambda expressions
		/*price = listOfDishes.stream()
				.mapToDouble(DishesList::getTotalPrice)
				.sum();*/
		for(OrderItems e: listOfDishes){
			price+=e.getTotalPrice();
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public enum StatusOfDelivery {
		PENDING,
		READY_FOR_SHIPMENT,
		DELIVERING,
		DELIVERED;
	}
	
	@Converter(autoApply = true)
	public static class OrderStatusConverter implements AttributeConverter<StatusOfDelivery, String> {
		@Override
		public String convertToDatabaseColumn(StatusOfDelivery stat) {
			 switch (stat) {
			   case PENDING:
				   return "P";
			   case READY_FOR_SHIPMENT:
				   return "R";
			   case DELIVERING:
				   return "D";
			   case DELIVERED:
				   return "S";
			   default:
				   throw new IllegalArgumentException("Unknown value: " + stat);
			   }
		}
	
	@Override
		public StatusOfDelivery convertToEntityAttribute(String dbType) {
			switch (dbType) {
			   case "P":
				   return StatusOfDelivery.PENDING;
			   case "R":
				   return StatusOfDelivery.READY_FOR_SHIPMENT;
			   case "D":
				   return StatusOfDelivery.DELIVERING;
			   case "S":
				   return StatusOfDelivery.DELIVERED;
			   default:
				   throw new IllegalArgumentException("Unknown value: " + dbType);
			   }
		}
	}

	@Override
	public String toString() {
		return "Order id - " + id + ", user - " + user.getId() + ", listOfDishes - " + listOfDishes + ", address - " + address
				+ ", timeOfOrder - " + timeOfOrder + ", timeOfDelivery - " + timeOfDelivery + ", price - " + price
				+ ", status - " + status;
	}
}
