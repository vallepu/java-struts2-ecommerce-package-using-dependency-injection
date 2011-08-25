package org.webhop.ywdc.beans;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.google.inject.Inject;



public class MembersRepository extends AbstractHibernateRepository<Members, Integer> 
{
	public String query;
	public String username;
	public String password;
	
	public Members findbyUsernamePassword(String username, String password)
    {
		Criteria criteria = getSession().createCriteria(Members.class);
    	criteria.add(Restrictions.eq("username", username));
    	criteria.add(Restrictions.eq("password", password));
    	Members currentmembers =  (Members) criteria.uniqueResult();
  
    	return currentmembers;
    }
	
}
