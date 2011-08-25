package org.webhop.ywdc.beans;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class OrderPaymentRepository extends AbstractHibernateRepository<OrderPayment, Integer> 
{
	

	public List<OrderPayment> getOrderPaymentsWithinDateRange(Date date1, Date date2)
	{
		
		/*
		Criteria searchCriteria = getSession().createCriteria(OrderPayment.class);

		searchCriteria.createCriteria("correspondence").add(Expression.between("dateReceived",
		date1,
		date2));
		
		List<OrderPayment> orderpaymentList = searchCriteria.list();
		
		*/
		
		Criteria searchCriteria2 = getSession().createCriteria(OrderPayment.class);
		searchCriteria2.add(Restrictions.between("orderdate", date1, date2));
		List<OrderPayment> orderpaymentList = searchCriteria2.list();
		return orderpaymentList;

	}
	public List<OrderPayment> findPaginatedOrderPaymentByDateRange(Date datefrom, Date dateto, Integer firstResult)
    {
		
		Criteria criteria = getSession().createCriteria(OrderPayment.class);
		criteria.add(Restrictions.between("orderdate", datefrom, dateto));
		criteria.addOrder(Order.desc("orderdate"));
		Integer arrayfirstResult = firstResult - 1;
		criteria.setFirstResult(arrayfirstResult);
	
		List<OrderPayment> orderpaymentList =  criteria.list();
  
    	return orderpaymentList;
    }
}
