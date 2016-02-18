package com.restaurant.serviceBeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import com.restaurant.entities.Order;
import com.restaurant.entities.User;
import com.restaurant.entities.User.TypeOfUser;
import com.restaurant.security.PasswordHash;
import com.restaurant.services.CustomerService;
import com.restaurant.services.LoginService;
import com.restaurant.services.SuperUserService;

@Named
@Scope("session")
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private User user;
	private User loginUser;
	private List<User> users;
	private String street;
	private String city;
	private String app;
	private String noOfHouse;
	private int zip;
	private String login;
	private String password;
	private List<Order> orders;
	private static final Logger log = LogManager.getLogger();
	@Inject
	private LoginService loginServ;
	@Inject
	private SuperUserService superUs;
	@Inject
	private CustomerService custServ;
	
	@PostConstruct
	public void init(){
		users = superUs.findAll();
		user = new User();
		user.setIsActive(false);
		orders = new ArrayList<>();
	}
	
	public UserBean() {}
	
	public String validation(){
		loginUser = loginServ.login(login, password);
		if(loginUser!=null){
			if(!loginUser.getIsActive()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Your account is been disactivated by super user", null));
		        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				return null;
			}
			switch(loginUser.getTypeOfUser().toString()){
				case("ADMINISTRATOR"):
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome, " + loginUser.getName()));
//					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					log.info("Login of user - " + loginUser.getLogin());
					return "/staffPages/Administrator-Main?faces-redirect=true";
				case("CUSTOMER"):
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome, " + loginUser.getName()));
//					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					log.info("Login of user - " + loginUser.getLogin());
					return "Customer-Menu?faces-redirect=true";
				case("ANALYST"):					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome, " + loginUser.getName()));
//					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					log.info("Login of user - " + loginUser.getLogin());
					return "/staffPages/Analyst-Main?faces-redirect=true";
				case("KITCHEN_STAFF"):
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome, " + loginUser.getName()));
//					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					log.info("Login of user - " + loginUser.getLogin());
					return "/staffPages/KitchenStaff-Main?faces-redirect=true";
				case("SUPER_USER"):
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome, " + loginUser.getName()));
//					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					log.info("Login of user - " + loginUser.getLogin());
					return "/staffPages/SuperUser-Main?faces-redirect=true";
				case("DELIVERY_RESPONSIBLE"):
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome, " + loginUser.getName()));
//					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					log.info("Login of user - " + loginUser.getLogin());
					return "/staffPages/Delivery-Main?faces-redirect=true";
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect login/password", null));
		log.info("Login failed for " + login);
		return null;
	}
	
	public String logout(){
		if(loginUser.getTypeOfUser()==TypeOfUser.CUSTOMER){
			log.info("Log out of user - " + loginUser.getLogin());
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Goodbye", "Thank you for visiting our website"));
			return "/Customer-Menu?faces-redirect=true";
		}
		else {
			log.info("Log out of user - " + loginUser.getLogin());
	        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Goodbye", "Have a nice day!"));
	        return "/admin-Staff?faces-redirect=true";
		}
	}
	
	public String viewProfile(){
		String[] address = loginUser.getDeliveryAddress().split(", ");
		city = address[0];
		street = address[1];
		noOfHouse = address[2];
		app = address[3];
		zip = Integer.valueOf(address[4]);
		orders=custServ.findByCustomer(loginUser.getId());
		return "/Customer-Profile?faces-redirect=true";
	}
	
	public String saveNewUser(){
//		if(!passwordConf.equals(user.getPassword())){
//	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords don't match!"));
//		}
		user.setDeliveryAddress(city + ", " + street + ", " + noOfHouse + ", " + app + ", " + zip);
		try {
			user.setPassword(PasswordHash.createHash(password));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error("Exception: ", e.getMessage());
		}
		try{
			superUs.saveUser(user);
		}catch(DatabaseException e){
			log.error("Exception: ", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User with the same login already exists. Enter new login", null));
			return null;
		}
		log.info("New user (" + user.getLogin() +") was saved by " + loginUser.getLogin());
		user = new User();
		password = null;
		return "/staffPages/SuperUser-Main?faces-redirect=true";
	}
	
//	public String findUser(String id){
//		int n = Integer.valueOf(id);
//		superUs.findUser(n);
//		return "SuperUser-ChangeUser";
//	}
	
	public void refreshList(){
		users = superUs.findAll();
	}
	
	public String editUser(String id){
		log.info("User (" + user.getLogin() +") was edited by " + loginUser.getLogin());
		password = null;
		int n = Integer.valueOf(id);
		user = superUs.findUser(n);
		String[] address = user.getDeliveryAddress().split(", ");
		city = address[0];
		street = address[1];
		noOfHouse = address[2];
		app = address[3];
		zip = Integer.valueOf(address[4]);
		return "/staffPages/SuperUser-ChangeUser?faces-redirect=true";
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
