package com.restaurant.DAO;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.restaurant.entities.OrderItems;

@Repository
public class OrderItemsDAOImpl implements OrderItemsDAO{
	
	@PersistenceContext
	private EntityManager em; 
	
	@Override
	public List<OrderItems> viewItems() {
		TypedQuery<OrderItems> query = em.createQuery("SELECT i FROM DishesList i "
				+ "WHERE i.isCooked=false  "
				+ "ORDER BY (SELECT o.timeOfOrder from Orders o)", OrderItems.class);
		List<OrderItems>listOfItems = query.getResultList();
		return listOfItems;
	}
	@Override
	public Collection<OrderItems> filterItemByStatus(){
		String query = "Select i from Item i, Orders o";
		query+="group by i.dishesList.isCooked and o.timeOfOrder";
		query+="where d.order_id=o.id";
		TypedQuery<OrderItems> filterQuery = em.createQuery(query, OrderItems.class);
		return filterQuery.getResultList();
		}
	
	@Override
	public void saveItem(OrderItems item){
		if(item.getId()==0){
			em.persist(item);
		}else
			em.merge(item);
	}
	
	@Override
	public OrderItems findById(int id){
		return em.find(OrderItems.class, id);
	}
	
	@Override
	public List<OrderItems> findAll(){
		TypedQuery<OrderItems> query = em.createQuery("SELECT o FROM DishesList o", OrderItems.class);
		return query.getResultList();
	}
	
}
