package org.webhop.ywdc.admin;

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
public class CategoryControlAction extends ActionSupport 
{
	
	public List<Category> categoryList;
	

	public String UserTempToken;
	private static final long serialVersionUID = 1L;
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		
		categoryList = currentService.getCategories();
		
		
		return SUCCESS;
	}
	public String getUserTempToken() 
	{
		return UserTempToken;
	}

	public void setUserTempToken(String userTempToken) 
	{
		UserTempToken = userTempToken;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
