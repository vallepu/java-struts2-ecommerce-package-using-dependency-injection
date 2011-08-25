package org.webhop.ywdc.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.Images;
import org.webhop.ywdc.beans.Product;
import org.webhop.ywdc.dtos.PublicProduct;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



public class CategoryProductAction extends ActionSupport 
{
	private static final long serialVersionUID = 2732536847491306797L;
	public String SESSIONID;
	public String SESSIONIDVALUE;
	public String USERID;
	public String USERVALUE;
	public String category;
	public String catbuilt;
	public String categoryName;
	public Integer categoryId;
	public Integer currentPage;
	public Integer totalProducts;
	public String pagination;
	public Integer firstResult;
	public List<Category> categories;
	public List<Object[]> productimageList;
	public List<PublicProduct> publicproductList;
	
	public List<PublicProduct> cartItems;
	public HashMap<Integer, Integer> cartMap;
	public Integer listCount;
	
	
	
	
	public String execute()
	{
		
		Integer productsperPage = 10;
		
		productimageList = new ArrayList<Object[]>();
		
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		categories = currentService.getCategories();
		
		CategoryBuilder cB = new CategoryBuilder();
		cB.setCategories(categories);
		catbuilt = cB.wrappingFunction();
		
	
		
		Cart cart = new Cart();
		cartMap = cart.getSessionCartMap();
		if(cartMap != null)
		{
			cartItems = currentService.getPublicProductByHashMap(cartMap);
		}
		
	
		
		if(category != null)
		{
			Category currentCategory = currentService.getCategoryById(Integer.parseInt(category));
			categoryName = currentCategory.getName();
		}
		if(categoryId == null)
		{
			categoryId = 0;	
		}
		if(categoryId != 0)
		{
			
	
			if(totalProducts == null)
			{
				totalProducts = currentService.countTotalProductsByCategoryId(categoryId);
			}
			if(currentPage == null)
			{
				currentPage = 1;
			}
			
			
			
			firstResult = ((currentPage - 1)*productsperPage) + 1;
			
			publicproductList = currentService.getPublicProductByCategoryId(categoryId, firstResult, productsperPage);
			listCount = publicproductList.size();
			
			if(categoryId != null && categoryId != 0)
			{
				Pagination paginationObject = new Pagination();
				paginationObject.setCategoryId(categoryId);
				paginationObject.setCurrentPage(currentPage);
				paginationObject.setProductsperPage(productsperPage);
				paginationObject.setTotalProducts(totalProducts);
				pagination = paginationObject.buildPaginatedLinkStructure();
			}
		
		
			
		}
		
  		
  		
		return SUCCESS;
	}
	public List<PublicProduct> getCartItems() 
	{
		return cartItems;
	}
	public void setCartItems(List<PublicProduct> cartItems) 
	{
		this.cartItems = cartItems;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<Category> getCategories() 
	{
		return categories;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}
	public String getCatbuilt() {
		return catbuilt;
	}
	public void setCatbuilt(String catbuilt) {
		this.catbuilt = catbuilt;
	}
	public String getPagination() {
		return pagination;
	}
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getCategoryId() 
	{
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) 
	{
		this.categoryId = categoryId;
	}
	public Integer getCurrentPage() 
	{
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) 
	{
		this.currentPage = currentPage;
	}
	public Integer getTotalProducts() 
	{
		return totalProducts;
	}
	public void setTotalProducts(Integer totalProducts) 
	{
		this.totalProducts = totalProducts;
	}
	public List<Object[]> getProductimageList() 
	{
		return productimageList;
	}
	public void setProductimageList(List<Object[]> productimageList) 
	{
		this.productimageList = productimageList;
	}
	public List<PublicProduct> getPublicproductList() 
	{
		return publicproductList;
	}
	public void setPublicproductList(List<PublicProduct> publicproductList) 
	{
		this.publicproductList = publicproductList;
	}
	public Integer getListCount() {
		return listCount;
	}
	public void setListCount(Integer listCount) {
		this.listCount = listCount;
	}
}
