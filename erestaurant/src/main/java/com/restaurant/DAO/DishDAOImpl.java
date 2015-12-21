package com.restaurant.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.restaurant.entities.Dish;

@Repository
public class DishDAOImpl implements DishDAO{
	@PersistenceContext
	private EntityManager em; 
	
	@Override
	public void saveDish(Dish newDish) {
		if(newDish.getId()==0){
			em.persist(newDish);
		}else
			em.merge(newDish);
	}
	
	public Dish findById(int id){
		return em.find(Dish.class, id);
	}
	
	public List<Dish> findAll(){
		TypedQuery<Dish> query=em.createQuery("SELECT d FROM Dish d WHERE d.isAvailable=true", Dish.class);
		return query.getResultList();
	}
	
	public List<Dish> findAllAdm(){
		TypedQuery<Dish> query=em.createQuery("SELECT d FROM Dish d", Dish.class);
		return query.getResultList();
	}

}
