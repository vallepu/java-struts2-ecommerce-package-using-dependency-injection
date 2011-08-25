package org.webhop.ywdc.client;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Cart 
{
	public Map session;
	public HashMap<Integer, Integer> sessioncartMap;
	
	
	public Cart()
	{
		
		setSession();
		setSessionCartMap();
		
	}
	
	public void addProductToCart(Integer productId, Integer quantity)
	{
		//add product by productId to SESSION
		if(sessioncartMap == null)
		{
			sessioncartMap = new HashMap(); // this will be the hashmap that we put our cart info intos
			sessioncartMap.put(productId, quantity);
			session.put("cart", sessioncartMap);
			
		}
		else
		{
			//wil this overwrite the product if it already exists? I think so since
			//i checked out the exception list in the Map documentation
			sessioncartMap.put(productId, quantity);
			
			session.put("cart", sessioncartMap);
			
		}
		
	}
	public void deleteProductFromCart(Integer productId)
	{
		//delete product by productId from SESSION
		sessioncartMap.remove(productId);
		if(sessioncartMap.size() == 0)
		{
			session.remove("cart");
		}
		else
		{
			
			session.put("cart", sessioncartMap);
		}
		
		
	}
	public void updateProductInCart(Integer productId, Integer quantity)
	{
		if(quantity == 0)
		{
			deleteProductFromCart(productId);
			
		}
		else
		{
			//update the session to include the new quantity information
			if(sessioncartMap == null)
			{
				sessioncartMap = new HashMap(); // this will be the hashmap that we put our cart info intos
				sessioncartMap.put(productId, quantity);
				session.put("cart", sessioncartMap);
				
			}
			else
			{
				//wil this overwrite the product if it already exists? I think so since
				//i checked out the exception list in the Map documentation
				sessioncartMap.put(productId, quantity);
				
				session.put("cart", sessioncartMap);
				
			}
		}
		
	}
	public String displayCart()
	{
		//build the cart html from displayCart( ) and SESSION information
		
		
		return "";
	}
	public String displayMiniCart()
	{
	
		return "";
	}
	public Map getSession() 
	{
		return session;
	}
	public void setSession() 
	{
		this.session = ActionContext.getContext().getSession();
	}
	public HashMap<Integer, Integer> getSessionCartMap() 
	{
		return sessioncartMap;
	}
	public void setSessionCartMap() 
	{
	
		this.sessioncartMap = (HashMap) session.get("cart");;
	}
}
