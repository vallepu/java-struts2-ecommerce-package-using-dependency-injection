package org.webhop.ywdc.beans;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CategoryRepository extends AbstractHibernateRepository<Category, Integer> 
{

	
	public List<Category> findPaginatedCategories(Integer firstResult)
    {
		
		Criteria criteria = getSession().createCriteria(Category.class);
	
		
		criteria.addOrder(Order.asc("name"));
		Integer arrayfirstResult = firstResult - 1;
		criteria.setFirstResult(arrayfirstResult);
	
		List<Category> categoryList =  criteria.list();
  
    	return categoryList;
    }
	
	public List<Category> getAllCategories()
	{
		Criteria criteria = getSession().createCriteria(Category.class);
	
		criteria.addOrder(Order.asc("name"));
		List<Category> categoryList =  criteria.list();
		  
    	return categoryList;
	}
	
	
	
	
}
