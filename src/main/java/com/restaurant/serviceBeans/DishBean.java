package com.restaurant.serviceBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.springframework.context.annotation.Scope;

import com.restaurant.entities.Category;
import com.restaurant.entities.Dish;
import com.restaurant.services.AdministratorService;
import com.restaurant.services.CustomerService;

@Named
@Scope("session")
public class DishBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Dish> menu;
	private List<Dish> custMenu;
	private Dish dish;
	private List<Category> categories;
	private Category cat;
	private Map<String, Integer> catMap;
	private Integer catId;
	private static final Logger log = LogManager.getLogger();
	@Inject
	private AdministratorService admServ;
	@Inject
	private UserBean userBean;
	@Inject
	private CustomerService custserv;
	
	public DishBean(){}

	@PostConstruct
	public void init(){
		dish = new Dish();
		categories = admServ.findAllCategory();
		dish.setIsAvailable(false);
		dish.setIsKitchenMade(true);
		catMap = new HashMap<>();
		for(Category e: categories){
			catMap.put(e.getName(), e.getId());
		}
		custMenu = custserv.findAllDishes();
	}

	public void refreshList(){
		menu = admServ.findAllDish();
//		dish = new Dish();
//		categories = admServ.findAllCategory();
		custMenu = custserv.findAllDishes();
	}
	
	public String saveNewDish(){
		Category cat = admServ.findCategoryByID(catId);
		dish.setCatOfDish(cat);
		try{
		admServ.saveDish(dish);
		}catch(DatabaseException e){
			log.error("Exception: ", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dish with the same name already exists. Enter new name", null));
			return null;
		}
		log.info("Administrator " + userBean.getLoginUser().getLogin() + " saved new dish " + dish.getId());
		return "/staffPages/Administrator-MainDish?faces-redirect=true";
//		return "/staffPages/administrator/Administrator-MainDish?faces-redirect=true";
	}
	
	public String editDish(String id){
		int n = Integer.valueOf(id);
		dish = admServ.findDishByID(n);
		return "/staffPages/Administrator-ChangeDish?faces-redirect=true";
//		return "/staffPages/administrator/Administrator-ChangeDish?faces-redirect=true";
	}
	
	public String back(){
		dish = null;
		cat = null;
		return "/staffPages/Administrator-MainDish?faces-redirect=true";
	}
	
	public List<Dish> getMenu() {
		return menu;
	}

	public void setMenu(List<Dish> menu) {
		this.menu = menu;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public Map<String, Integer> getCatMap() {
		return catMap;
	}

	public void setCatMap(Map<String, Integer> catMap) {
		this.catMap = catMap;
	}

	public Integer getCatId() {
		return catId;
	}

	public List<Dish> getCustMenu() {
		return custMenu;
	}

	public void setCustMenu(List<Dish> custMenu) {
		this.custMenu = custMenu;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

}
