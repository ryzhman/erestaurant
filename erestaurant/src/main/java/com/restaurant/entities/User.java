package com.restaurant.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String login;
	private String password;
	private String name;
	private String surname;
	
	@Convert(converter = UserTypeConverter.class)
	private TypeOfUser typeOfUser;
	
	private java.sql.Timestamp dateOfCreation;
	private java.sql.Timestamp dateOfLastLogin;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	private String deliveryAddress;
	private boolean isActive;
	
	public User(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
		this.dateOfCreation = java.sql.Timestamp.valueOf(LocalDateTime.now());
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public java.sql.Timestamp getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(LocalDateTime dateOfCreation) {
		this.dateOfCreation = java.sql.Timestamp.valueOf(dateOfCreation);
	}
	public java.sql.Timestamp getDateOfLastLogin() {
		return dateOfLastLogin;
	}
	public void setDateOfLastLogin(LocalDateTime dateOfLastLogin) {
		this.dateOfLastLogin = java.sql.Timestamp.valueOf(dateOfLastLogin);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
	    this.dateOfBirth = dateOfBirth;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public TypeOfUser getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(TypeOfUser type) {
		typeOfUser = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User id - " + id + ", login - " + login + ", name - " + name
				+ ", surname - " + surname + ", typeOfUser - " + typeOfUser + ", dateOfCreation - " + dateOfCreation
				+ ", dateOfLastLogin - " + dateOfLastLogin + ", email - " + email + ", dateOfBirth - " + dateOfBirth
				+ ", deliveryAddress - " + deliveryAddress + ", isActive - " + isActive;
	}
	
	public static enum TypeOfUser {
		ADMINISTRATOR,
		CUSTOMER,
		ANALYST,
		KITCHEN_STAFF,
		SUPER_USER,
		DELIVERY_RESPONSIBLE;
	}
	
	@Converter(autoApply = true)
	public static class UserTypeConverter implements AttributeConverter<TypeOfUser, String> {
		@Override
		public String convertToDatabaseColumn(TypeOfUser type) {
			 switch (type) {
			   case ADMINISTRATOR:
				   return "A";
			   case CUSTOMER:
				   return "C";
			   case ANALYST:
				   return "N";
			   case KITCHEN_STAFF:
				   return "K";
			   case SUPER_USER:
				   return "S";
			   case DELIVERY_RESPONSIBLE:
				   return "D";
			   default:
				   throw new IllegalArgumentException("Unknown value: " + type);
			   }
		}

		@Override
		public TypeOfUser convertToEntityAttribute(String dbType) {
			switch (dbType) {
			   case "A":
				   return TypeOfUser.ADMINISTRATOR;
			   case "C":
				   return TypeOfUser.CUSTOMER;
			   case "N":
				   return TypeOfUser.ANALYST;
			   case "K":
				   return TypeOfUser.KITCHEN_STAFF;
			   case "S":
				   return TypeOfUser.SUPER_USER;
			   case "D":
				   return TypeOfUser.DELIVERY_RESPONSIBLE;
			   default:
				   throw new IllegalArgumentException("Unknown value: " + dbType);
			   }
		}
	}
}
