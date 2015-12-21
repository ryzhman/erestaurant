package com.restaurant.services;

import java.util.List;

import com.restaurant.entities.Category;
import com.restaurant.entities.Dish;
import com.restaurant.entities.User;

public interface AdministratorService {
	public User login(String login, String password);
	
	public void saveDish(Dish newDish);
	
	public void changeStatusOfDish(Dish dish, boolean isActive);
	
	public void addCategory(Category cat);
	
	public Dish findDishByID(int id);
	
	public Category findCategoryByID(int id);
	
	public void deleteCategory(int id);

	public List<Dish> findAllDish();
	
	public List<Category> findAllCategory();
	
	public Category findByName(String name);
}
