package com.restaurant.servicesTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.restaurant.DAO.LoginDAOImpl;
import com.restaurant.entities.User;
import com.restaurant.services.SuperUserService;

public class SuperUserServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	public final SuperUserService testSU = context.getBean(SuperUserService.class);
	public final LoginDAOImpl loginDAO = new LoginDAOImpl();
	
	public final String login = "kyr";
	public final String pass = "oleg";
	
	@Test
	public void login() {
		User testUser = testSU.login("superUser", "restaurantSU");
		assertEquals(User.TypeOfUser.SUPER_USER, testUser.getTypeOfUser());
		
	}

}
