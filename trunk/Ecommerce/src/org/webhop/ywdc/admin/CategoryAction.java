package org.webhop.ywdc.admin;

import java.util.ArrayList;
import java.util.List;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class CategoryAction extends ActionSupport
{
	public int id;
	public List<Category> categories;
	public String UserTempToken;
	

	public String execute()
	{
		
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		categories = currentService.getCategories();
		/*
		categories = new ArrayList<Category>();
		Category c = new Category();
		c.setId(45);
		c.setDescription("testing desc");
		c.setName("testing");
		categories.add(c);
		*/
		return SUCCESS;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}

	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
	
}
