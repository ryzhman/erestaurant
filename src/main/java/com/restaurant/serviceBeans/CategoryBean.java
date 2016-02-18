package com.restaurant.serviceBeans;

import java.io.Serializable;
import java.util.List;

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
import com.restaurant.services.AdministratorService;

@Named
@Scope("session")
public class CategoryBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Category cat;
	private List<Category> listOfCat;
	private static final Logger log = LogManager.getLogger();
	@Inject
	private AdministratorService admServ;
	@Inject
	private UserBean userBean;
	
	@PostConstruct
	public void init(){
		cat = new Category();
	}
	
	public CategoryBean(){}
	
	public void refreshCat(){
		listOfCat = admServ.findAllCategory();
	}
	
	public String saveNewCat(){
		try{
			admServ.addCategory(cat);
		}catch(DatabaseException e){
			log.error("Exception: ", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category with the same name already exists. Enter new name", null));
			return null;
		}
		log.info("Administrator " + userBean.getLoginUser().getLogin() + " saved new category " + cat.getId());
		return "/staffPages/administrator/Administrator-MainCat?faces-redirect=true";
	}
	
	public String editCat(String id){
		int n = Integer.valueOf(id);
		cat = admServ.findCategoryByID(n);
		log.info("Administrator " + userBean.getLoginUser().getLogin() + " edited category " + cat.getId());
		return "/staffPages/administrator/Administrator-ChangeCat?faces-redirect=true";
	}
	
	public String deleteCat(String id){
		int n = Integer.valueOf(id);
		admServ.deleteCategory(n);
		log.info("Administrator " + userBean.getLoginUser().getLogin() + " deleted category " + cat.getId());
		return "/staffPages/administrator/Administrator-MainCat?faces-redirect=true";	
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public List<Category> getListOfCat() {
		return listOfCat;
	}

	public void setListOfCat(List<Category> listOfCat) {
		this.listOfCat = listOfCat;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
