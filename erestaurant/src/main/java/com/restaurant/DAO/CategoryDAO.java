package com.restaurant.DAO;

import java.util.List;

import com.restaurant.entities.Category;

public interface CategoryDAO {
	public void saveCategory(Category cat); 
	
	public Category findById(int id);
	
	public List<Category> findAll();
	
	public void deleteCategory(int id);
	
	public Category findByName(String name);
}
