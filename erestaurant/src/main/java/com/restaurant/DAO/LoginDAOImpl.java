package com.restaurant.DAO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.entities.User;
import com.restaurant.security.PasswordHash;

@Repository
public class LoginDAOImpl implements LoginDAO{
	private User user;
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public User login(String login, String password) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM Users u WHERE u.login=:login", User.class);
		query.setParameter("login", login);
		try{
			user = query.getSingleResult();
			if(PasswordHash.validatePassword(password, user.getPassword())){
				setDateOfLastLogin(LocalDateTime.now());
				if(user.getId()==0) em.persist(user);
				else em.merge(user);
				return user;
			}else
				return null;
		}catch(NoResultException|InvalidKeySpecException|NoSuchAlgorithmException e){
			e.getMessage();
			return null;
		}
//		List<User> result = query.getResultList();
//		User user = null;
//		if(!result.isEmpty()){
//			user = result.get(0);
//			if(user.getPassword().equals(password)){
//				user.setDateOfLastLogin(LocalDateTime.now());
//				if(user.getId()==0) em.persist(user);
//				else em.merge(user);
//				return user;
//			}
//		}else
//			return null;
	}
	
	public void setDateOfLastLogin(LocalDateTime time){
			user.setDateOfLastLogin(time);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
	

