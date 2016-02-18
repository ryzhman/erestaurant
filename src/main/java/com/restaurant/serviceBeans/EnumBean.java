package com.restaurant.serviceBeans;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import com.restaurant.entities.Order;
import com.restaurant.entities.Order.StatusOfDelivery;
import com.restaurant.entities.User;
import com.restaurant.entities.User.TypeOfUser;

@Named
@ApplicationScoped
public class EnumBean {

	public TypeOfUser[] getTypes(){
		return User.TypeOfUser.values();
	}
	
	public StatusOfDelivery[] getStatuses(){
		return Order.StatusOfDelivery.values();
	}

}
