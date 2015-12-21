package com.restaurant.DAO;

import java.util.List;

import com.restaurant.entities.Dish;

public interface DishDAO {
	
	public void saveDish(Dish newDish);
	
	public Dish findById(int id);

	public List<Dish> findAll();
	
	public List<Dish> findAllAdm();

}
