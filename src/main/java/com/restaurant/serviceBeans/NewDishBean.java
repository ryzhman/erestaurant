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

@Named
@Scope("request")
public class NewDishBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Dish dish;
	private List<Category> categories;
	private Map<String, Integer> catMap;
	private Integer catId;
	private static final Logger log = LogManager.getLogger();
	@Inject
	private AdministratorService admServ;
	
	public NewDishBean(){	}

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
	}
	
	
//	public String createDish(){
//		dish = new Dish();
//		categories = admServ.findAllCategory();
//		dish.setIsAvailable(false);
//		dish.setIsKitchenMade(true);
//		categories = new HashMap<Category, String>();
//		Iterator<Category> iter = categoriesList.iterator();
//		while(iter.hasNext()){
//			categories.put(iter.next(), iter.next().toString());
//		}
//		categories.forEach((k,v)->System.out.println(k+"="+v));
//		return "AddDish";
//	}

	public String saveNewDish(){
		dish.setCatOfDish(admServ.findCategoryByID(catId));
		
		try{
			admServ.saveDish(dish);
		}catch(DatabaseException e){
			log.error("Exception: ", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User with the same login already exists. Enter new login", null));
			return null;
		}
		return "/staffPages/Administrator-MainDish?faces-redirect=true";
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
	
	public void setCatMap(Map<String, Integer> catMap) {
		this.catMap = catMap;
	}

	public Map<String, Integer> getCatMap() {
		return catMap;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

}
