package org.webhop.ywdc.client;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.dtos.PublicProduct;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport 
{
	private static final long serialVersionUID = 6453031951328457729L;
	public List<PublicProduct> cartItems;
	public HashMap<Integer, Integer> cartMap;
	public String catbuilt;
	public String deleteProduct;
	public String updateProduct;
	public Integer id;
	public Integer quantity;
	public double total;
	public String totalString;
	
	
	public List<Category> categories;
	
	public String execute()
	{
		
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices aServices = injector.getInstance(AuthenticationServices.class);
		aServices.setConnection(connection);
		aServices.setInjector(injector);
		
		categories = aServices.getCategories();
		
		CategoryBuilder cB = new CategoryBuilder();
		cB.setCategories(categories);
		catbuilt = cB.wrappingFunction();
		
		Cart cart = new Cart();
		//cartMap = cart.getSessionCartMap();
		//if(cartMap != null)
		//{
			//cartItems = aServices.getPublicProductByHashMap(cartMap);
			
			if(updateProduct != null)
			{
				if(quantity != null)
				{
					cart.updateProductInCart(id, quantity);
				}
				
			}
			if(deleteProduct != null)
			{
			    cart.deleteProductFromCart(id);
			
			}
			Map session = ActionContext.getContext().getSession();
		
			cartMap = (HashMap) session.get("cart");
			total = 0.0;
			if(cartMap != null)
			{
				
			
				cartItems = aServices.getPublicProductByHashMap(cartMap);
			
			
				for(PublicProduct cartItem: cartItems)
				{
					Integer productId = cartItem.getId();
					double price = cartItem.getPrice();
					
					cartItem.setPrice(price);
					Integer quantity = cartMap.get(cartItem.id);
					total = total + (price * quantity);
					
				}
			}
			 NumberFormat twoDForm = NumberFormat.getCurrencyInstance(Locale.US);  

			 totalString = twoDForm.format(total);
			
			
		
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
	public HashMap<Integer, Integer> getCartmap() 
	{
		return cartMap;
	}
	public void setCartmap(HashMap<Integer, Integer> cartmap) 
	{
		this.cartMap = cartmap;
	}
	public String getCatbuilt() 
	{
		return catbuilt;
	}
	public void setCatbuilt(String catbuilt) 
	{
		this.catbuilt = catbuilt;
	}
	public List<Category> getCategories() 
	{
		return categories;
	}
	public void setCategories(List<Category> categories) 
	{
		this.categories = categories;
	}
	public String getDeleteProduct() 
	{
		return deleteProduct;
	}
	public void setDeleteProduct(String deleteProduct) 
	{
		this.deleteProduct = deleteProduct;
	}
	public String getUpdateProduct() 
	{
		return updateProduct;
	}
	public void setUpdateProduct(String updateProduct) 
	{
		this.updateProduct = updateProduct;
	}
	public HashMap<Integer, Integer> getCartMap() {
		return cartMap;
	}
	public void setCartMap(HashMap<Integer, Integer> cartMap) {
		this.cartMap = cartMap;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getTotalString() {
		return totalString;
	}
	public void setTotalString(String totalString) {
		this.totalString = totalString;
	}
}
