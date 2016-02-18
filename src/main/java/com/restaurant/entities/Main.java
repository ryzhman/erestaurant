package com.restaurant.entities;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.restaurant.services.AdministratorService;
import com.restaurant.services.CustomerService;
import com.restaurant.services.LoginService;
import com.restaurant.services.SuperUserService;

public class Main {

	@SuppressWarnings("resource")
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//    	SuperUserService superUserServ = context.getBean(SuperUserService.class);
//    	
//    	try{
//    		User u2edit = superUserServ.findUser(7);
//    		u2edit.setName("Oleg");
//    		u2edit.setSurname("Malkovych");
//    		u2edit.setDateOfBirth(Date.from(LocalDate.of(1990, 5, 30).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//    		u2edit.setLogin("sUser");
//    		u2edit.setPassword(PasswordHash.createHash("sUser"));
//    		u2edit.setDeliveryAddress("Kopernika 10");
//    		u2edit.setEmail("sUser@gmail.com");
//    		u2edit.setIsActive(true);
//    		u2edit.setTypeOfUser(TypeOfUser.SUPER_USER);
//    		superUserServ.saveUser(u2edit);
//    	}catch(InvalidKeySpecException|NoSuchAlgorithmException e){
//    		e.getMessage();
//    	}
    	
    	
//    	LoginService login = context.getBean(LoginService.class);
//    	User sUser = superUserServ.login("user1", "user1");
//    	System.out.println(sUser);
    	
//    	AdministratorService admServ = context.getBean(AdministratorService.class);
    	
//    	admServ.editCategory(5, "Salads");
//    	admServ.editCategory(8, "Antipasti");
//    	admServ.addDish("Pizza 4 seasons", 29.35, true, 400, "Tomatoes, cheese, onion, garlic, olives, pineapple", 
//    			7, true, null);
//    	admServ.editDish(1, "Caesar Salad", 10.44, true, 200, "Salad, onion, garlic, tomato, cheese, crisps", 5, null);
//    	admServ.editDish(4, "Can of Pepsi", 2.99, false, 250, "Pepsi", 4, null);
//    	admServ.editDish(3, "Apple pie", 10.99, true, 100, "Apples, wholegrain flour, dried fruits, eggs", 3, null);
//    	admServ.editDish(2, "Ukrainian borsch", 13.23, true, 500, "Beetroot, onion, garlic, tomato, potato, cabbage", 2, null);
//    	admServ.editDish(5, "Pepperoni pizza", 9.5, true, 700, "Cheese, tomatoes, chilly peppers, olives, onion", 6, null);
//    	admServ.editDish(6, "Bread", 3, false, 75, "Wholegrain flour, water, salt", 1, null);

    	
    	CustomerService custServ = context.getBean(CustomerService.class);
    	for(Dish e:custServ.findAllDishes()){
    		System.out.println(e);
    	}
    	
    	System.out.println("\n");
    	
//    	AdministratorService admServ = context.getBean(AdministratorService.class);
//    	for(Dish e:admServ.findAllDish()){
//    		System.out.println(e);
//    	}
    	
//    	List<Order> list = custServ.findByCustomer(9);
//    	list.stream().forEach(e->System.out.println(e));
    	
    	
//    	User me = custServ.login("petro", "mazepa");
//    	System.out.println(me);
    	/*Order o1 = new Order();
    	o1.setUser(me);
    	o1.setAddress(me.getDeliveryAddress());
    	
    	Item b = new Item();
    	Dish borsch = custServ.findDishById(2);
    	b.setDish(borsch);
    	b.setQuantity(2);
    	b.setOrder(o1);
    	Item p = new Item();
    	Dish pizza = custServ.findDishById(5);
    	p.setDish(pizza);
    	p.setQuantity(5);
    	Dish salad = custServ.findDishById(1);
    	Item s = new Item();
    	s.setDish(salad);
    	s.setQuantity(1);
    	
    	List<Item> orderList = new ArrayList<>();
    	orderList.add(b);
    	orderList.add(p);
    	orderList.add(s);
    	
    	o1.setListOfDishes(orderList);
    	orderList.get(0).setOrder(o1);
    	orderList.get(1).setOrder(o1);
    	orderList.get(2).setOrder(o1);
//    	custServ.saveOrder(o1);
    	
    	Order o2 = new Order();
    	User mazepa = custServ.login("mazepa", "petro");
    	o2.setUser(mazepa);
    	o2.setAddress(mazepa.getDeliveryAddress());
    	
    	Dish pepsi = custServ.findDishById(4);
    	Item peps = new Item();
    	peps.setDish(pepsi);
    	peps.setQuantity(10);
    	Item bread = new Item();
    	Dish br = custServ.findDishById(6);
    	bread.setDish(br);
    	bread.setQuantity(3);
    	Item dranyky = new Item();
    	Dish dr = custServ.findDishById(10);
    	dranyky.setDish(dr);
    	dranyky.setQuantity(4);
    	
    	List<Item> l2 = new ArrayList<>();
    	l2.add(peps);
    	l2.add(bread);
    	l2.add(dranyky);
    	o2.setListOfDishes(l2);
    	l2.get(0).setOrder(o2);
    	l2.get(1).setOrder(o2);
    	l2.get(2).setOrder(o2);
//    	custServ.saveOrder(o2);

    	Order o3 = new Order();
    	User anon = custServ.login("i-V@n", "pizza_Man");
    	
    	
    	o3.setUser(anon);
    	o3.setAddress("Sofiivs'ka square 23");
    	
    	Item pepss = new Item();
    	pepss.setDish(pepsi);
    	pepss.setQuantity(40);
    	Item piz = new Item();
    	piz.setDish(pizza);
    	piz.setQuantity(6);
    	List<Item> l3 = new ArrayList<>();
    	l3.add(pepss);
    	l3.add(piz);
    	o3.setListOfDishes(l3);
    	l3.get(0).setOrder(o3);
    	l3.get(1).setOrder(o3);
//    	custServ.saveOrder(o3);

		Order o4 = new Order();
    	User jimm = custServ.login("jimmy", "Jimm");
    	o4.setAddress("Poltava, Vil'na 10");
    	o4.setUser(jimm);

    	Item pepper = new Item();
		Dish pepp = custServ.findDishById(5);
		pepper.setDish(pepp);
		pepper.setQuantity(1);
		Item bor = new Item();
		bor.setDish(borsch);
		bor.setQuantity(1);
		ArrayList<Item>l4 = new ArrayList<>();
		l4.add(pepper);
		l4.add(bor);
		o4.setListOfDishes(l4);
		l4.get(0).setOrder(o4);
		l4.get(1).setOrder(o4);
//		custServ.saveOrder(o4);
*/
    	
//    	KitchenStaffService kitch = context.getBean(KitchenStaffService.class);
//    	User kStaff = kitch.login("oleg", "kyr");
//    	System.out.println(kStaff);
//    	List<OrderItems> list = kitch.viewItems();
//    	for(OrderItems i: list){
//    		System.out.println(i);
//    	}

//    	kitch.processItem(list.get(5));
//    	kitch.processItem(list.get(6));
		
//		DeliveryStaffService del = context.getBean(DeliveryStaffService.class);
//    	List<Order> listOfOrders = del.getListOfOrdersByStatus();
//    	listOfOrders.stream().forEach(e->System.out.println(e));
//    	del.changeStatusOfOrder(listOfOrders.get(3));
    	
		
//		AnalystService anServ = context.getBean(AnalystService.class);
//		List<Report> repoList = anServ.getReportByCountAndSum(Date.valueOf(LocalDate.now().minusDays(40)), Date.valueOf(LocalDate.now().minusDays(1)));
//		List<Report> repoList = anServ.getReportByMenuItemCatogories(LocalDate.now().minusDays(30), LocalDate.now().minusDays(10));
//    	repoList.stream()
//    	.forEach(e->System.out.println(e));
    	
    	
    }
}
