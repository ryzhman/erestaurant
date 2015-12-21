package com.restaurant.serviceBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.restaurant.entities.Dish;
import com.restaurant.entities.Order;
import com.restaurant.entities.OrderItems;
import com.restaurant.entities.User;
import com.restaurant.services.CustomerService;

@Named
@Scope("session")
public class CartBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private List<OrderItems> listOfItems;
	private Dish dish;
	private Map<Dish, Integer> mapOfItems;
	private List<Dish> keyList;
	private int quantity = 1;
	private User currentUser;
	private double totalCost;
	private Order order;
	private String city;
	private String street;
	private String noOfHouse;
	private String app;
	private int zip;
	DecimalFormat df = new DecimalFormat("####0.00");
	private static final Logger log = LogManager.getLogger();
	@Inject
	public CustomerService custServ;
	@Inject
	private UserBean userBean;
	@Inject
	private DishBean dishBean;
	
	public CartBean(){	}
	
	@PostConstruct
	public void init(){
		listOfItems = new ArrayList<>();
		mapOfItems = new HashMap<>();
//		newItem = new OrderItems();
//		setCurrentUser(LoginBean().getUser());
	}
	
	public void addToCart(String id){
		int n = Integer.valueOf(id);
		dish = new Dish();
		dish = custServ.findDishById(n);
		if(mapOfItems.containsKey(dish.getId())){
			mapOfItems.put(dish, mapOfItems.get(dish)+1);
			totalCost+=round(dish.getPrice());
		}else{
			totalCost+=round(dish.getPrice()*quantity);
			mapOfItems.put(dish, quantity);
		}
//		newItem = new OrderItems();
//		newItem.setDish(dish);
//		newItem.setQuantity(quantity);
//		listOfItems.add(newItem);
//		totalCost+=newItem.getTotalPrice();
	}
	
	public void increaseQuantity(Dish dish){
		if(mapOfItems.containsKey(dish)){
			mapOfItems.put(dish, mapOfItems.get(dish)+1);
			totalCost+=round(dish.getPrice());
		}
	}
	
	public void decreaseQuantity(Dish dish){
		if(mapOfItems.containsKey(dish)){
			mapOfItems.put(dish, mapOfItems.get(dish)-1);
			totalCost+=round(dish.getPrice());
//			round(totalCost);
		}
		if(mapOfItems.get(dish)==0){
			deleteFromCart(dish);
		}
	}
	
	public String deleteFromCart(Dish dish){
//		int n = Integer.parseInt(id);
//		dish = custServ.findDishById(n);
//		totalCost-= listOfItems.get(n).getTotalPrice();
//		System.out.println(totalCost);
		if(mapOfItems.containsKey(dish)){
			totalCost+=round(dish.getPrice()*mapOfItems.get(dish));
			mapOfItems.remove(dish);
		}
		return "Customer-Cart?faces-redirect=true";
	}

//	public void refreshCart(){
//		if(listOfItems.isEmpty()){
//			System.out.println("Empty");
//		}
//	}
	public String cleanCart(){
		mapOfItems.clear();
		totalCost=0;
//		listOfItems.clear();
		return "Customer-Cart?faces-redirect=true";
	}
	
	public String proceedWithAddress(){
//		currentUser = loginBean.getUser();
		if(userBean.getLoginUser()!=null){
			String[] address = userBean.getLoginUser().getDeliveryAddress().split(", ");
			city = address[0];
			street = address[1];
			noOfHouse = address[2];
			app = address[3];
			zip = Integer.valueOf(address[4]);
			return "Customer-EnterAddress?faces-redirect=true";
		}else return "Customer-EnterAddress?faces-redirect=true";
	}
	
	public String submitOrder(){
		order = new Order();
		if(userBean.getLoginUser()!=null){
			order.setUser(userBean.getLoginUser());
		}else{
			User anonymous = custServ.login("anonymous", "anon");
			order.setUser(anonymous);
		};
		if(city==null||street==null||noOfHouse==null||app==null||zip==0) return null;
		
		order.setAddress(city + ", " + street + ", " + noOfHouse + ", " + app + ", " + zip);
		order.setTimeOfOrder(Timestamp.valueOf(LocalDateTime.now()));
		order.setPrice(round(totalCost));
		
		for(Map.Entry<Dish, Integer> entry: mapOfItems.entrySet()) {
			Dish key = entry.getKey();
		    Integer value = entry.getValue();
		    
		    OrderItems orderItems = new OrderItems();
		    orderItems.setDish(key);
		    orderItems.setQuantity(value);
		    
		    listOfItems.add(orderItems);
		}
		
		order.setListOfDishes(listOfItems);
		for(int i=0;i<listOfItems.size(); i++){
			listOfItems.get(i).setOrder(order);;
		}
		custServ.saveOrder(order);
		log.info("Customer " + userBean.getLoginUser().getLogin() + " submitted an order #" + order.getId());
		eraseUserData();
		return "Customer-Thanks?faces-redirect=true";
	}
	
	public void eraseUserData(){
		listOfItems = null;
		dish = null;
		mapOfItems = null;
		keyList = null;
		quantity = 1;
		currentUser = null;
		totalCost = 0;
		order = null;
		city= null;
		street = null;
		noOfHouse= null;
		app = null;
		zip= 0;
	}
	
	public static double round(double value) {
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	/*public static double round(double val) {
		val = val*100;
		val = java.lang.Math.round(val);
		val = val /100;		
		return val;
	}*/
	
	
	public List<OrderItems> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(List<OrderItems> listOfItems) {
		this.listOfItems = listOfItems;
	}
	
//	public OrderItems getNewItem() {
//		return newItem;
//	}
//
//	public void setNewItem(OrderItems newItem) {
//		this.newItem = newItem;
//	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Map<Dish, Integer> getMapOfItems() {
		return mapOfItems;
	}

	public void setMapOfItems(Map<Dish, Integer> mapOfItems) {
		this.mapOfItems = mapOfItems;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNoOfHouse() {
		return noOfHouse;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public void setNoOfHouse(String noOfHouse) {
		this.noOfHouse = noOfHouse;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public List<Dish> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<Dish> keyList) {
		this.keyList = keyList;
	}
}
