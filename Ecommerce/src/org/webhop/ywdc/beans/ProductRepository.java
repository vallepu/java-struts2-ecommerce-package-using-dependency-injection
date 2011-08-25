package org.webhop.ywdc.beans;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;



public class ProductRepository extends AbstractHibernateRepository<Product, Integer> 
{
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllProducts()
	{
		List<Object> lObjects =  getSession().createSQLQuery("SELECT * FROM PRODUCT").addScalar("ID", Hibernate.INTEGER).addScalar("NAME", Hibernate.TEXT).list();
		
		return lObjects;
		
	}
	
	public List<Product> findProductByCategoryId(Integer categoryId)
    {
		
		
    	
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("categoryid", new Integer(categoryId)));
		criteria.addOrder(Order.asc("name"));
    	List<Product> productList =  criteria.list();
  
    	return productList;
    	
    }
	
	@SuppressWarnings("unchecked")
	public List<Product> findPaginatedProductByCategoryId(Integer categoryId, Integer firstResult)
    {
		
		Criteria criteria = getSession().createCriteria(Product.class);
	
		criteria.add(Restrictions.eq("categoryid", new Integer(categoryId)));
		criteria.addOrder(Order.asc("name"));
		Integer arrayfirstResult = firstResult - 1;
		criteria.setFirstResult(arrayfirstResult);
	
		List<Product> productList =  criteria.list();
  
    	return productList;
    }
	
	public List<Product> findProductByCategoryAndPrice(Integer categoryId, Double price)
    {
		Criteria criteria = getSession().createCriteria(Product.class);
		 criteria.add(Restrictions.eq("price", new Double(price)));
		 criteria.addOrder(Order.asc("name"));
    	criteria.add(Restrictions.eq("categoryid", new Integer(categoryId)));
    	List<Product> productList =  criteria.list();
  
    	return productList;
    }
	public List<Product> findProductByCategoryAndQuantity(Integer categoryId, Integer quantity)
    {
		Criteria criteria = getSession().createCriteria(Product.class);
		 criteria.add(Restrictions.eq("quantity", new Integer(quantity)));
		 criteria.addOrder(Order.asc("name"));
    	criteria.add(Restrictions.eq("categoryid", new Integer(categoryId)));
    	List<Product> productList =  criteria.list();
  
    	return productList;
    }
	public List<Product> findProductByCategoryAndName(Integer categoryId, String name)
    {
		Criteria criteria = getSession().createCriteria(Product.class);
		 criteria.add(Restrictions.eq("name", name));
		 criteria.addOrder(Order.asc("name"));
    	criteria.add(Restrictions.eq("categoryid", categoryId));
    	List<Product> productList =  criteria.list();
  
    	return productList;
    }
	public List<Product> findProductByCategoryAndDescription(Integer categoryId, String description)
    {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("description", description));
		criteria.addOrder(Order.asc("name"));
    	criteria.add(Restrictions.eq("categoryid", new Integer(categoryId)));
    	List<Product> productList =  criteria.list();
  
    	return productList;
    }
	public List<Product> findProductByCategoryAndSummary(Integer categoryId, String summary) 
	{
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("summary", summary));
		criteria.addOrder(Order.asc("name"));
    	criteria.add(Restrictions.eq("categoryid", new Integer(categoryId)));
    	List<Product> productList =  criteria.list();
  
    	return productList;
	}

	public void deleteProductsByCategoryId(Integer categoryId)
	{
		 String hql = "delete from Product where categoryid =" + categoryId;
	     Query query = getSession().createQuery(hql);
	     query.executeUpdate();
	       
	}

	
}
