package org.webhop.ywdc.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.Customers;
import org.webhop.ywdc.beans.Product;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class CategoryControlEditCellEntryAction extends ActionSupport 
{
	private String id;
	
	public String UserTempToken;
	private String name;
	public String description;
	public String parentid;

	
	private String oper;
	
	public String execute()
	{
		
		
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		
		Map requestParameters = ActionContext.getContext().getParameters();
		String[] operArray = (String[])requestParameters.get("oper");
		oper = operArray[0];
		
		if(oper.compareTo("edit") == 0)
		{
			if(name != null && description != null)
			{
				Category currentCategory = currentService.getCategoryById(Integer.parseInt(id));
				currentCategory.setName(name);
				currentCategory.setDescription(description);
				currentService.updateCategory(currentCategory);
			}
			else
			{
				if(name != null)
				{
					Category currentCategory = currentService.getCategoryById(Integer.parseInt(id));
					currentCategory.setName(name);
					currentService.updateCategory(currentCategory);
				}
				if(parentid != null)
				{
					Category currentCategory = currentService.getCategoryById(Integer.parseInt(id));
					currentCategory.setParentid(Integer.parseInt(parentid));
					currentService.updateCategory(currentCategory);
				}
				if(description != null)
				{
					Category currentCategory = currentService.getCategoryById(Integer.parseInt(id));
					currentCategory.setDescription(description);
					currentService.updateCategory(currentCategory);
				}
			}
			
		}
		if(oper.compareTo("add") == 0)
		{
			Category newCategory = new Category();
			newCategory.setName(name);
			newCategory.setDescription(description);
			newCategory.setParentid(Integer.parseInt(parentid));
			currentService.addCategory(newCategory);
		}
		if(oper.compareTo("del") == 0)
		{
			Category currentCategory = currentService.getCategoryById(Integer.parseInt(id));
			
			//currentService.deleteProductsByCategoryId(Integer.parseInt(id));
			//must delete all subcategories of this category, and all subcategories of that subcategory...
			
			/////////////////////////////////////////////////////////////////////////////////
			List<Category> tmpcategoryList = currentService.getCategories();
			
			
			List<Category> catList = new ArrayList<Category>();
			for(Category c: tmpcategoryList)
			{
				if(c.getId() != currentCategory.getId())
				{
					catList.add(c);
				}
			}
			
			ChildCategoryFinder ccFinder = new ChildCategoryFinder();
			ccFinder.setCategoryList(catList);
			List<Category> childcatList = ccFinder.findAllRelatedCategories(currentCategory);
			//now add the currentcategory to the childcatList. this will contain our category we 
			//are trying to delete and subsubcategories
			
			childcatList.add(currentCategory);
			
			for(Category c: childcatList)
			{
				
				//make sure that the products of these categories get deleted as well.
				
				currentService.deleteProductsByCategoryId(c.getId());
				currentService.deleteCategory(c);
			}
			
			/////////////////////////////////////////////////////////////////////////
			
			
			
			
			//currentService.deleteCategory(currentCategory);
		}
		return SUCCESS;
		
	}
	
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getOper() 
	{
		return oper;
	}
	public void setOper(String oper) 
	{
		this.oper = oper;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
}
