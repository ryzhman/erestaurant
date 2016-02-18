package com.restaurant.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Dish {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private boolean isKitchenMade;
	private double weight;
	private String ingredients;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category catOfDish;
	
	private java.sql.Timestamp dateOfLastEdit;
	private boolean isAvailable;
	private String picturePath;
	
	public Dish(){}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean getIsKitchenMade() {
		return isKitchenMade;
	}
	public void setIsKitchenMade(boolean isKitchenMade) {
		this.isKitchenMade = isKitchenMade;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public java.sql.Timestamp getDateOfLastEdit() {
		return dateOfLastEdit;
	}
	public void setDateOfLastEdit(java.sql.Timestamp dateOfLastEdit) {
		this.dateOfLastEdit = dateOfLastEdit;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	public Category getCatOfDish() {
		return catOfDish;
	}

	public void setCatOfDish(Category catOfDish) {
		this.catOfDish = catOfDish;
	}

	public void setKitchenMade(boolean isKitchenMade) {
		this.isKitchenMade = isKitchenMade;
	}

	@Override
	public String toString() {
		return "Dish id is " + id + ", name - " + name + ", price - " + price + ", isKitchenMade - " + isKitchenMade
				+ ", weight - " + weight + ", ingredients - " + ingredients + ", catOfDish - " + catOfDish
				+ ", dateOfLastEdit - " + dateOfLastEdit + ", isAvailable - " + isAvailable + ", picturePath - " + picturePath;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catOfDish == null) ? 0 : catOfDish.hashCode());
		result = prime * result + ((dateOfLastEdit == null) ? 0 : dateOfLastEdit.hashCode());
		result = prime * result + id;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + (isAvailable ? 1231 : 1237);
		result = prime * result + (isKitchenMade ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picturePath == null) ? 0 : picturePath.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object dish) {
		if((dish == null) || (dish.getClass() != this.getClass())) { 
			return false; 
			}
		Dish other = (Dish) dish;
		if(this.getName().equals(other.getName())){
			return true;
		}else
			return false;
	}
}
