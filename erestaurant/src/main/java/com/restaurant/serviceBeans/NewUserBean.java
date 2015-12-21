package com.restaurant.serviceBeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import com.restaurant.entities.User;
import com.restaurant.entities.User.TypeOfUser;
import com.restaurant.security.PasswordHash;
import com.restaurant.services.SuperUserService;

@Named
@Scope("session")
public class NewUserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private User user;
	private String street;
	private String city;
	private String app;
	private String noOfHouse;
	private int zip;
	private String password;
	private static final Logger log = LogManager.getLogger();
	@Inject
	private SuperUserService superUs;
	@Inject
	private UserBean userBean;
	
	@PostConstruct
	public void init(){
		user = new User();
//		user.setIsActive(false);
	}
	
	public NewUserBean() {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String saveNewUser(){
		user.setDeliveryAddress(city + ", " + street + ", " + noOfHouse + ", " + app + ", " + zip);
		try {
			user.setPassword(PasswordHash.createHash(password));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		superUs.saveUser(user);
		log.info("New user (" + user.getLogin() +") was saved by " + userBean.getLoginUser().getLogin());
		user = new User();
		password = null;
		return "/staffPages/SuperUser-Main?faces-redirect=true";
		
//		try {
//			user.setPassword(PasswordHash.createHash(user.getPassword()));
//		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//			e.printStackTrace();
//		}
//		user.setDeliveryAddress(city + ", " + street + ", " + noOfHouse + ", " + app + ", " + zip);
//		superUs.saveUser(user);
//		log.info("New user (" + user.getLogin() +") was saved by " + userBean.getLoginUser().getLogin());
	}
	
	public String saveNewCustomer(){
		user.setTypeOfUser(TypeOfUser.CUSTOMER);
		user.setIsActive(true);
		user.setDeliveryAddress(city + ", " + street + ", " + noOfHouse + ", " + app + ", " + zip);
		try {
			user.setPassword(PasswordHash.createHash(user.getPassword()));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		superUs.saveUser(user);
		log.info("New customer (" + user.getLogin() +") was saved");
		return "Customer-Menu?faces-redirect=true";
	}
	
	public String register(){
		return "Customer-Registration";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getNoOfHouse() {
		return noOfHouse;
	}

	public void setNoOfHouse(String noOfHouse) {
		this.noOfHouse = noOfHouse;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getPasswordConf() {
//		return passwordConf;
//	}
//
//	public void setPasswordConf(String passwordConf) {
//		this.passwordConf = passwordConf;
//	}
}
