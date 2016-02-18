package com.restaurant.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.restaurant.entities.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@PersistenceContext
	private EntityManager em; 

	@Override
	public void saveUser(User newUser){
		if(newUser.getId()==0){
			em.persist(newUser);
		}else
			em.merge(newUser);
	}
	@Override
	public User findById(int id){
		return em.find(User.class, id);
	}
	
	@Override
	public List<User> findAll(){
		TypedQuery<User> query = em.createQuery("SELECT u FROM Users u", User.class);
		return query.getResultList();
	}
	
	/*@Override
	public Order formOrder(String login, List<DishesList> listOfDishes, String address) {
		Order order = new Order();
		order.setUser(findUser(login));
		order.setListOfDishes(listOfDishes);
		order.setStatus(StatusOfDelivery.PENDING);

		if(address==null){
			order.setAddress(findUser(login).getDeliveryAddress());
		}
		System.out.println("The price of order (Id-" + order.getId() + ") is: " + order.getPrice());
		return order;
	}
*/

	/*@Override
	public void changeStatus(Order order2edit, StatusOfDelivery status) {
		order2edit.setStatus(status);
		if(status.equals(StatusOfDelivery.DELIVERED)){
			order2edit.setTimeOfDelivery(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		}
		System.out.println("Status of order id-" + order2edit.getId() + " was changed to " + order2edit.getStatus());
		em.merge(order2edit);
	}*/

	
	
	
	/*@Override
	public Collection<Order> filterOrdersByStatus() {
		TypedQuery<Order> filterOrders = em.createQuery("select o from orders o order by o.getStatus", Order.class);
		return filterOrders.getResultList();
	}*/
}
