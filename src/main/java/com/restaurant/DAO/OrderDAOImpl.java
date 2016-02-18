package com.restaurant.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.restaurant.entities.Order;
import com.restaurant.entities.Order.StatusOfDelivery;

@Repository
public class OrderDAOImpl implements OrderDAO{
	@PersistenceContext
	private EntityManager em; 
	
	@Override
	public Order findById(int id){
		return em.find(Order.class, id);
	}
	
	@Override 
	public List<Order> findByCustomer(int id){
		TypedQuery<Order> query = em.createQuery("SELECT r FROM Orders r WHERE r.user.id=:id", Order.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	
	@Override
	public void saveOrder(Order order) {
		if(order.getId()==0)
			em.persist(order);
		else 
			em.merge(order);
	}
	@Override
	public List<Order> getListOfOrders() {
		TypedQuery<Order> query = em.createQuery("SELECT o FROM Orders o WHERE o.status=:status OR o.status=:status1", Order.class);
		query.setParameter("status", StatusOfDelivery.READY_FOR_SHIPMENT);
		query.setParameter("status1", StatusOfDelivery.DELIVERING);
		return query.getResultList();
	}

}
