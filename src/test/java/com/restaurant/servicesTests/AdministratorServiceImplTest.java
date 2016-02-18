package com.restaurant.servicesTests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.restaurant.DAO.CategoryDAO;
import com.restaurant.DAO.DishDAO;
import com.restaurant.DAO.LoginDAO;
import com.restaurant.entities.Category;
import com.restaurant.entities.Dish;
import com.restaurant.services.AdministratorServiceImpl;

public class AdministratorServiceImplTest {
	@Inject
	static LoginDAO loginDAO;
	@Inject
	static DishDAO dishDAO;
	@Inject
	static CategoryDAO catDAO;
	@Inject
	static AdministratorServiceImpl admin;
	
	static Dish testDish;
	static List<Category> allCats;
	static List<Dish> allDishes;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testDish = new Dish();
//		allCats = catDAO.findAll();
//		allDishes = dishDAO.findAllAdm();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void testChangeStatusOfDish() {
		testDish.setAvailable(true);
		Dish testDish2 = new Dish();
		admin.changeStatusOfDish(testDish2, true);
		assertTrue(testDish2.getIsAvailable()==testDish.getIsAvailable());
	}

	@Test
	public void testFindAllDish() {
		fail("Not yet implemented");
	}

}
