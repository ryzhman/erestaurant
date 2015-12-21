package com.restaurant.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.restaurant.entities.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	@PersistenceContext
	private EntityManager em; 
	
	@Override
	public void saveCategory(Category cat) {
		if(cat.getId()==0){
			em.persist(cat);
		}else
			em.merge(cat);
	}
	
	@Override
	public Category findById(int id){
		return em.find(Category.class, id);
	}
	
	@Override
	public List<Category> findAll(){
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
		return query.getResultList();
	}
	
	@Override
	public void deleteCategory(int id){
		em.remove(em.find(Category.class, id));
	}
	
	@Override
	public Category findByName(String name){
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

}
