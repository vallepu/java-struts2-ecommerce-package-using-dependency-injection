package org.webhop.ywdc.beans;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class OrderStatusRepository extends AbstractHibernateRepository<OrderStatus, Integer> 
{
	public List<OrderStatus> getIncompleteOrders()
	{
		
		Criteria criteria = getSession().createCriteria(OrderStatus.class);
		criteria.add(Restrictions.eq("status", "Incomplete"));
		criteria.addOrder(Order.asc("statusdate"));
    	List<OrderStatus> orderstatusList =  criteria.list();
  
		
		
		
		return orderstatusList;
		
	}
	
	public List<OrderStatus> findPaginatedIncompleteOrders(Integer firstResult) 
	{
		
		Criteria criteria = getSession().createCriteria(OrderStatus.class);
		
		criteria.add(Restrictions.eq("status", "Incomplete"));
		criteria.addOrder(Order.desc("statusdate"));
		Integer arrayfirstResult = firstResult - 1;
		criteria.setFirstResult(arrayfirstResult);
	
		List<OrderStatus> orderstatusList =  criteria.list();
  
    	return orderstatusList;
		
	}

	public List<OrderStatus> findPaginatedCompletedOrders(Integer firstResult) 
	{
		Criteria criteria = getSession().createCriteria(OrderStatus.class);
		
		criteria.add(Restrictions.eq("status", "Complete"));
		criteria.addOrder(Order.desc("statusdate"));
		Integer arrayfirstResult = firstResult - 1;
		criteria.setFirstResult(arrayfirstResult);
	
		List<OrderStatus> orderstatusList =  criteria.list();
  
    	return orderstatusList;
	}
	public List<OrderStatus> findPaginatedRefundedOrders(Integer firstResult) 
	{
		
		Criteria criteria = getSession().createCriteria(OrderStatus.class);
		
		criteria.add(Restrictions.eq("status", "Refund"));
		criteria.addOrder(Order.desc("statusdate"));
		Integer arrayfirstResult = firstResult - 1;
		criteria.setFirstResult(arrayfirstResult);
	
		List<OrderStatus> orderstatusList =  criteria.list();
  
    	return orderstatusList;
		
	}
	
	public List<OrderStatus> findOrderStatusWithinDateRange(Date date1, Date date2)
	{
		
		Criteria searchCriteria2 = getSession().createCriteria(OrderStatus.class);
		searchCriteria2.add(Restrictions.between("statusdate", date1, date2));
		List<OrderStatus> orderpaymentList = searchCriteria2.list();
		return orderpaymentList;
	}
	public List<OrderStatus> countOrderStatusWithinDateRange(String orderstatus, Date date1, Date date2)
	{
		
		Criteria searchCriteria2 = getSession().createCriteria(OrderStatus.class);
		searchCriteria2.setFetchMode("OrderId", FetchMode.JOIN);
		searchCriteria2.add(Restrictions.between("orderdate", date1, date2));
		List<OrderStatus> orderpaymentList = searchCriteria2.list();
		return orderpaymentList;
		
		/*
		 
		 List orders = session.createCriteria(Order.class)
        .setFetchMode(“products”,FetchMode.JOIN)
        .add(Restrictions.eq(“id”,”1111”))
        .list();
		 
		 */
		
		
		
	}
	public List<OrderStatus> findOrderStatusWithinTimestampRange(Timestamp date1, Timestamp date2)
	{
		
		Criteria searchCriteria2 = getSession().createCriteria(OrderStatus.class);
		searchCriteria2.add(Restrictions.between("statusdate", date1, date2));
		List<OrderStatus> orderpaymentList = searchCriteria2.list();
		return orderpaymentList;
	}

}
