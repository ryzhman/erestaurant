package com.restaurant.serviceBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.restaurant.entities.Category;
import com.restaurant.services.AdministratorService;

@Named
@Scope("request")
public class NewCategoryBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Category cat;
	private static final Logger log = LogManager.getLogger();
	@Inject
	private UserBean userBean;
	@Inject
	private AdministratorService admServ;
	
	@PostConstruct
	public void init(){
		cat = new Category();		

	}
	
	public NewCategoryBean(){}
	
	public String saveNewCat(){
		admServ.addCategory(cat);
		log.info("Administrator " + userBean.getLoginUser().getLogin() + " saved new category " + cat.getId());
		return "/staffPages/Administrator-MainCat?faces-redirect=true";
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}
}
