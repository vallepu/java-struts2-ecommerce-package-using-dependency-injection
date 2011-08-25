package org.webhop.ywdc.admin;

import java.util.List;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.client.CategoryBuilder;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
@Secured
public class InventoryControlAction extends ActionSupport
{
	private static final long serialVersionUID = -3301152276962756342L;
	public String category;
	public String categoryid;
	
	
	
	public String categoryName;

	public String UserTempToken;
	public List<Category> categories;
	
	public String catId;

	public String buildSuckerFish()
	{
		
		for(Category c: categories)
		{
			Integer catId = c.getParentid();
		}
		
		
		return "Test";
	}
	
	public String execute()
	{
		
		
		//must check to see if this page is being forwarded from ProductImages page. If so we must add the category id from the value stack
		 ValueStack theValueStack = ActionContext.getContext().getValueStack();
		 Integer categoryId = (Integer) theValueStack.findValue("categoryId");
		 if(categoryId != null)
		 {
			 category = categoryId.toString();
		 }

		catId="index.action";
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		categories = currentService.getCategories();
		
		
		//
		CategoryBuilder cB = new CategoryBuilder();
		cB.setCategories(categories);
		
		
		return SUCCESS;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	
	public String getUserTempToken()
	{
		return UserTempToken;
	}
	public void setUserTempToken(String UserTempToken)
	{
		this.UserTempToken = UserTempToken;
	}
	public String getCategory()
	{ 
		return this.category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public List<Category> getCategories() 
	{
		return categories;
	}
	public void setCategories(List<Category> categories) 
	{
		this.categories = categories;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	
}
