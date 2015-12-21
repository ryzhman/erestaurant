package com.restaurant.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	
	public Category(){}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	/*
	 * This block is not necessary due to existence 
	of distinct table with categories
	*
	*/
	/*public enum CategoryOfDishes {
		SOUPS,
		SALADS,
		DESSERTS,
		APPETIZES,
		BAKERY,
		MAIN_COURSES,
		BEVERAGES,
		HOT_DRINKS;
	}
	
	@Converter(autoApply = true)
	public class CategoryOfDishesConverter implements AttributeConverter<CategoryOfDishes, String>{
			@Override
			public String convertToDatabaseColumn(CategoryOfDishes cat) {
				 switch (cat) {
				   case SOUPS:
					   return "S";
				   case SALADS:
					   return "L";
				   case DESSERTS:
					   return "D";
				   case APPETIZES:
					   return "A";
				   case BAKERY:
					   return "K";
				   case MAIN_COURSES:
					   return "M";
				   case BEVERAGES:
					   return "B";
				   case HOT_DRINKS:
					   return "H";
				   default:
					   throw new IllegalArgumentException("Unknown value: " + cat);
				   }
			}

			@Override
			public CategoryOfDishes convertToEntityAttribute(String dbType) {
				switch (dbType) {
				   case "S":
					   return CategoryOfDishes.SOUPS;
				   case "L":
					   return CategoryOfDishes.SALADS;
				   case "D":
					   return CategoryOfDishes.DESSERTS;
				   case "A":
					   return CategoryOfDishes.APPETIZES;
				   case "K":
					   return CategoryOfDishes.BAKERY;
				   case "M":
					   return CategoryOfDishes.MAIN_COURSES;
				   case "B":
					   return CategoryOfDishes.BEVERAGES;
				   case "H":
					   return CategoryOfDishes.HOT_DRINKS;
				   default:
					   throw new IllegalArgumentException("Unknown value: " + dbType);
				   }
			}
	}*/
}


