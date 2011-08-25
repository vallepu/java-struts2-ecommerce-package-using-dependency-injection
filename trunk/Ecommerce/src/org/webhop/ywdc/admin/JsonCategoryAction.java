package org.webhop.ywdc.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.Customers;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.opensymphony.xwork2.ActionSupport;

public class JsonCategoryAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	public Map<String, String> select;
	public String UserTempToken;
	

	public String execute()
	{
		select = new HashMap<String, String>();
		
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		List<Category> catList = currentService.getCategories();
		
		for(Category c: catList)
		{
			select.put(Integer.toString(c.getId()), c.getName());
			
		}
		return SUCCESS;
	}
	
	public Map<String, String> getSelect()
	{
		return select;
	}
	public void setSelect(Map<String, String> select)
	{
		this.select = select;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}

	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
}
