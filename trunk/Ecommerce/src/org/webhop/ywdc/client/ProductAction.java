package org.webhop.ywdc.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.ProductShipping;
import org.webhop.ywdc.dtos.PublicProduct;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport 
{
	private static final long serialVersionUID = -4585863496033390167L;
	public Integer id;
	public PublicProduct publicProduct;
	public Cart cart;
	public String addProduct;
	public Integer quantity;
	public List<PublicProduct> cartItems;
	public List<Category> categories;
	public HashMap<Integer, Integer> cartMap;
	public String catbuilt;
	public String serviceId;
	public String totalshippingCost;
	public ProductShipping productShipping;
	
	
	
	public String execute()
	{
	
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		
		categories = currentService.getCategories();
		
		CategoryBuilder cB = new CategoryBuilder();
		cB.setCategories(categories);
		catbuilt = cB.wrappingFunction();
		
		
		//must iterate thru cart contents and build cartItems list that way
		//other method would be to add these extra items to the session itsself, which i dont
		//think would be the best idea, but keep options open.
		
		//Map session = ActionContext.getContext().getSession();
		//HashMap sessioncartMap = (HashMap) session.get("cart");
		
		Cart cart = new Cart();
		
		
		
		
		
		if(addProduct != null)
		{
			if(addProduct.compareTo("Add To Cart") == 0)
			{
					cart.addProductToCart(id, quantity);
			}	
			Map session = ActionContext.getContext().getSession();
			cartMap = (HashMap) session.get("cart");
			cartItems = currentService.getPublicProductByHashMap(cartMap);
		}
		else
		{
			cartMap = cart.getSessionCartMap();
			if(cartMap != null)
			{
				cartItems = currentService.getPublicProductByHashMap(cartMap);
				
			}
		}
		
		Integer ids = id;
		publicProduct = currentService.getPublicProductById(ids);
		productShipping = currentService.getProductShippingByProductId(ids);
		//need to get the productshipping information. if empty then set productto be discontinued
		
		return SUCCESS;
	}
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}
	public List<Category> getCategories() 
	{
		return categories;
	}
	public void setCategories(List<Category> categories) 
	{
		this.categories = categories;
	}
	public String getCatbuilt() 
	{
		return catbuilt;
	}
	public void setCatbuilt(String catbuilt) 
	{
		this.catbuilt = catbuilt;
	}
	public Integer getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(Integer quantity) 
	{
		this.quantity = quantity;
	}
	public PublicProduct getPublicProduct() 
	{
		return publicProduct;
	}
	public void setPublicProduct(PublicProduct publicProduct) 
	{
		this.publicProduct = publicProduct;
	}
	public Cart getCart() 
	{
		return cart;
	}
	public void setCart(Cart cart) 
	{
		this.cart = cart;
	}
	public String getAddProduct() 
	{
		return addProduct;
	}
	public void setAddProduct(String addProduct) 
	{
		this.addProduct = addProduct;
	}
	public List<PublicProduct> getCartItems() 
	{
		return cartItems;
	}
	public void setCartItems(List<PublicProduct> cartItems) 
	{
		this.cartItems = cartItems;
	}
	public HashMap<Integer, Integer> getCartMap() 
	{
		return cartMap;
	}
	public void setCartMap(HashMap<Integer, Integer> cartMap) 
	{
		this.cartMap = cartMap;
	}
	public ProductShipping getProductShipping() {
		return productShipping;
	}
	public void setProductShipping(ProductShipping productShipping) {
		this.productShipping = productShipping;
	}
}
