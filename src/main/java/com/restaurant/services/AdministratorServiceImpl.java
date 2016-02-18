package com.restaurant.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.DAO.CategoryDAO;
import com.restaurant.DAO.DishDAO;
import com.restaurant.DAO.LoginDAO;
import com.restaurant.entities.Category;
import com.restaurant.entities.Dish;
import com.restaurant.entities.User;

@Named
public class AdministratorServiceImpl implements AdministratorService{
	@Inject
	private LoginDAO loginDAO;
	@Inject
	private DishDAO dishDAO;
	@Inject
	private CategoryDAO catDAO;
	
	@Override
	public User login(String login, String password) {
		return loginDAO.login(login, password);
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public void saveDish(Dish newDish) {
		dishDAO.saveDish(newDish);
	}
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public void changeStatusOfDish(Dish dish2edit, boolean isActive) {
		dish2edit.setIsAvailable(isActive);
		dish2edit.setDateOfLastEdit(Timestamp.valueOf(LocalDateTime.now()));
		dishDAO.saveDish(dish2edit);
	}

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public void addCategory(Category cat) {
		catDAO.saveCategory(cat);
	}

	
	@Override
	public List<Dish> findAllDish(){
		return dishDAO.findAllAdm();
	}
	
	@Override
	public List<Category> findAllCategory(){
		return catDAO.findAll();
	}

	@Override
	public Dish findDishByID(int id) {
		return dishDAO.findById(id);
	}

	@Override
	public Category findCategoryByID(int id) {
		return catDAO.findById(id);
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public void deleteCategory(int id){
		catDAO.deleteCategory(id);
	}
	
	@Override
	public Category findByName(String name){
		return catDAO.findByName(name);
	}
}
