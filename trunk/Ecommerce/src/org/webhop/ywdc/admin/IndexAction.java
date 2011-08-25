package org.webhop.ywdc.admin;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.client.CategoryBuilder;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class IndexAction extends ActionSupport 
{
	public String UserTempToken;
	
	public String catbar;
	public String getCatbar() {
		return catbar;
	}
	public void setCatbar(String catbar) {
		this.catbar = catbar;
	}
	private static final long serialVersionUID = -8375869004433395100L;
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		List<Category> categories = currentService.getCategories();
		
		CategoryBuilder cBuilder = new CategoryBuilder();
		cBuilder.setCategories(categories);
		catbar = cBuilder.wrappingFunction();
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
  		Cookie[] ckey = request.getCookies();
  		for(Cookie c: ckey)
  		{
  			System.out.println(c.getName());
  			if(c.getName().compareTo("UserTempToken") == 0)
  			{
  				
  				UserTempToken = c.getValue();
  			}
  		}
		return SUCCESS;
	}
	public String getUserTempToken()
	{
		return UserTempToken;
	}
	public void setUserTempToken(String UserTempToken)
	{
		this.UserTempToken = UserTempToken;
	}

}
