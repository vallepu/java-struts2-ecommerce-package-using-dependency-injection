package org.webhop.ywdc.beans;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.webhop.ywdc.exceptions.InfrastructureException;
import org.webhop.ywdc.util.HibernateConnection;
import org.webhop.ywdc.util.IConnection;



public abstract class AbstractHibernateRepository<T, Id extends Serializable>
{
    private Class<T> persistentClass;
    private HibernateConnection connection;

    @SuppressWarnings("unchecked")
    public AbstractHibernateRepository()
    {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public Class<T> getPersistentClass()
    {
        return persistentClass;
    }
    public void flushSession()
    {
        getSession().flush();
    }
    public void clearSession()
    {
        getSession().clear();
    }
    @SuppressWarnings("unchecked")
    public T findById(Id id, boolean lock)
    {
        T entity;

        if (lock)
        {
            entity = (T) getSession().get(getPersistentClass(), id, LockMode.UPGRADE);
        }
        else
        {
            entity = (T) getSession().get(getPersistentClass(), id);
        }

        return entity;
    }
    @SuppressWarnings("unchecked")
	public List<T> findListByName(String tablecolumnname, String name)
    {
    	Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(tablecolumnname, name));
        
        List<T> objectList = criteria.list();
        return objectList;
    }
    public T findInstanceByName(String tablecolumnname, String name)
    {
    	Criteria criteria = getSession().createCriteria(persistentClass);
    	criteria.add(Restrictions.eq(tablecolumnname, name));
    	T object = (T) criteria.uniqueResult();
    	return object;
    }
    @SuppressWarnings("unchecked")
	public List<T> findListByNamedInteger(String tablecolumnname, Integer name)
    {
    	Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(tablecolumnname, new Integer(name)));
        List<T> objectList = criteria.list();
        return objectList;
    }
    public List<T> findListByNamedDouble(String tablecolumnname, Double name)
    {
    	Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(tablecolumnname, new Double(name)));
        List<T> objectList = criteria.list();
        return objectList;
    }
    public T findInstanceByNamedInteger(String tablecolumnname, Integer name)
    {
    	Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(tablecolumnname, new Integer(name)));
        T objectList = (T) criteria.uniqueResult();
        return objectList;
    }
    @SuppressWarnings("unchecked")
	public List<T> findByQuery(String query)
    {
    	List<T> listObjects = getSession().createQuery(query).list();
    	return listObjects;
    }
    @SuppressWarnings("unchecked")
    public List<T> findAll()
    {
        Criteria criteria = getSession().createCriteria(persistentClass);
        return criteria.list();
    }
    public T makePersistent(T entity)
    {
        getSession().saveOrUpdate(entity);
        return entity;
    }
    public void makeTransient(T entity)
    {
        getSession().delete(entity);
    }
    public void makeTransient(Id id)
    {
        Object entity = getSession().get(getPersistentClass(), id);
        getSession().delete(entity);
    }
    public void refresh(T entity)
    {
        getSession().refresh(entity);
    }
    public T delete(T entity)
   {
	   Transaction tx = getSession().beginTransaction();
	   getSession().delete(entity);
	   tx.commit();
	   return entity;
   }
    public T save(T entity)
    {
    	Transaction tx=getSession().beginTransaction();
   	 	getSession().save(entity);
   	 	tx.commit();
    	return entity;
    }
    public T update(T entity)
    {
    	 Transaction tx=getSession().beginTransaction();
    	 getSession().update(entity);
    	 tx.commit();
    	 return entity;
    }
    public T merge(T entity)
    {
    	Transaction tx=getSession().beginTransaction();
   	 	getSession().merge(entity);
   	 	tx.commit();
        return entity;
    }
    @SuppressWarnings("unchecked")
	public void setConnection(IConnection connection) throws InfrastructureException
    {
    	this.connection = (HibernateConnection) connection;
    }
    @SuppressWarnings("unchecked")
	public IConnection getConnection() throws InfrastructureException
    {
    	return this.connection;
    }
    public Session getSession()
    {
        return connection.getSession();
    }
    public void closeSession()
    {
        connection.closeSession();
    }
}
