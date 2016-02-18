package com.restaurant.DAO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.restaurant.entities.Report;

@Repository
public class ReportsDAOImpl implements ReportsDAO {
	@PersistenceContext
	private EntityManager em; 

	@Override
	public List<Report> getReportByCountAndSum(Date startDate, Date endDate) {
		String queryText = "Select new com.restaurant.entities.Report(FUNC('date', o.timeOfOrder), sum(o.price), count(o.id)) ";
//		queryText+="(FUNCTION('day', o.timeOfOrder), FUNCTION('month', o.timeOfOrder), FUNCTION('year', o.timeOfOrder), count(o.id), sum(o.price))";
		queryText+=" from Orders o ";
		queryText+=" where o.timeOfOrder BETWEEN :start AND :end ";
		queryText+="group by FUNC('date', o.timeOfOrder)";
//		queryText+=" group by FUNCTION('day', o.timeOfOrder), FUNCTION('month', o.timeOfOrder), FUNCTION('year', o.timeOfOrder)";
		TypedQuery<Report> query = em.createQuery(queryText, Report.class);
		query.setParameter("start", /*Timestamp.valueOf(startDate.atStartOfDay())*/ startDate)/*.toInstant())*/;
		query.setParameter("end", /*Timestamp.valueOf(endDate.atStartOfDay())*/ endDate);   /*Timestamp.from(endDate.toInstant()));*/
		return query.getResultList();
	}

	@Override
	public List<Report> getReportByMenuItemCatogories(Date startDate, Date endDate) {
//		String queryText = "Select new com.restaurant.entities.Report (FUNC('day', o.timeOfOrder), FUNC('month', o.timeOfOrder), FUNC('year', o.timeOfOrder), "
//				+ "c.name, sum(l.quantity), sum(l.totalPrice))";
		String queryText = "Select new com.restaurant.entities.Report (c.name, sum(l.quantity), sum(l.totalPrice))";   
		queryText+=" FROM Orders o, DishesList l, Dish d, Category c";
		queryText+=" where o=l.order AND d=l.dish AND c=d.catOfDish AND o.timeOfOrder BETWEEN :start AND :end";
		queryText+=" group by c.name"; 
//		queryText+=" order by sum(l.totalPrice)";
		TypedQuery<Report> query = em.createQuery(queryText, Report.class);
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		return query.getResultList();
	}

}
