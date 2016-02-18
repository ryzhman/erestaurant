package com.restaurant.servicesTests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.restaurant.DAO.CategoryDAO;
import com.restaurant.DAO.DishDAO;
import com.restaurant.entities.Category;
import com.restaurant.entities.Dish;

public class AdministratorServiceTest {
	@Inject
	DishDAO dishDAO;
	@Inject
	CategoryDAO catDAO;
	
	Dish searchedDish;
	List<Category> allCats;
	List<Dish> allDishes;
	
	@Before 
	public void initAll(){
		if(catDAO==null){
			System.out.println("catDAO null");
		}
		allCats = catDAO.findAll();
		allDishes = dishDAO.findAllAdm();
	}

	@Test
	public void findDishByIDTest() {
		for(Dish d: allDishes){
			if(d.getId()==2){
				searchedDish=d;
			}
		}
		assertTrue(dishDAO.findById(2)==searchedDish);
	}

}
