package org.webhop.ywdc.admin;

import java.text.DecimalFormat;
import java.util.List;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.Product;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class InventoryControlEditCellEntryAction extends ActionSupport
{
	public String id;
	public String category;
	public String name;
	public String summary;
	public String description;
	public String price;
	public String quantity;
	public String oper;
	public String UserTempToken;
	
	public String searchString;
	public String searchField;
	public String categoryid;
	public List<Category> categories;
	
	
	
	private static final long serialVersionUID = -8992547928384932788L;

	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		categories = currentService.getCategories();
		
		
		if(oper.compareTo("edit") == 0)
		{
			if(name != null && description != null && summary != null && quantity != null &&  price !=  null && categoryid != null)
			{
				Product currentProduct = currentService.getProductById(Integer.parseInt(id));
				currentProduct.setName(name);
				currentProduct.setCategoryid(Integer.parseInt(categoryid));
				currentProduct.setDescription(description);
				currentProduct.setSummary(summary);
				currentProduct.setQuantity(Integer.parseInt(quantity));
				currentProduct.setPrice(Double.parseDouble(price));
				currentService.updateProduct(currentProduct);
				
				
			}
			else
			{
				if(name != null)
				{
					Product currentProduct = currentService.getProductById(Integer.parseInt(id));
					currentProduct.setName(name);
					currentService.updateProduct(currentProduct);
				}
				if(summary != null)
				{
					Product currentProduct = currentService.getProductById(Integer.parseInt(id));
					currentProduct.setSummary(summary);
					currentService.updateProduct(currentProduct);
				}
				if(description != null)
				{
					Product currentProduct = currentService.getProductById(Integer.parseInt(id));
					currentProduct.setDescription(description);
					currentService.updateProduct(currentProduct);
				}
				if(quantity != null)
				{
					try
					{
						
					
					Product currentProduct = currentService.getProductById(Integer.parseInt(id));
					currentProduct.setQuantity(Integer.parseInt(quantity));
					currentService.updateProduct(currentProduct);
					}
					finally
					{
						System.out.println("test");
					}
				}
				if(price != null)
				{
					Product currentProduct = currentService.getProductById(Integer.parseInt(id));
					
					double priceChanged = Double.parseDouble(price);
					DecimalFormat twoDForm = new DecimalFormat("#.##");
					priceChanged = Double.valueOf(twoDForm.format(priceChanged));
					
					currentProduct.setPrice(priceChanged);
					currentService.updateProduct(currentProduct);
				}
				if(categoryid != null)
				{
					Product currentProduct = currentService.getProductById(Integer.parseInt(id));
					currentProduct.setCategoryid(Integer.parseInt(categoryid));
					currentService.updateProduct(currentProduct);
					
				}
			}
		}
		if(oper.compareTo("add") == 0)
		{
			Product newProduct = new Product();
			//newProduct.setCategoryid(Integer.parseInt(category));
			newProduct.setName(name);
			newProduct.setDescription(description);
			newProduct.setSummary(summary);
			newProduct.setPrice(Double.parseDouble(price));
			newProduct.setQuantity(Integer.parseInt(quantity));
			newProduct.setCategoryid(Integer.parseInt(categoryid));
			currentService.addProduct(newProduct);
			
			
		}
		if(oper.compareTo("del") == 0)
		{
			Product currentProduct = currentService.getProductById(Integer.parseInt(id));
			currentService.deleteProduct(currentProduct);
		}
		return SUCCESS;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getId()
	{
		return this.id;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public String getCategory()
	{
		return this.category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
	
}
