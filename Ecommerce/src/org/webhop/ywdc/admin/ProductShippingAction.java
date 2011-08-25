package org.webhop.ywdc.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.webhop.ywdc.beans.Images;
import org.webhop.ywdc.beans.Members;
import org.webhop.ywdc.beans.Product;
import org.webhop.ywdc.beans.ProductShipping;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;
import org.webhop.ywdc.util.RandomString;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Secured
public class ProductShippingAction extends ActionSupport {

	
	private static final long serialVersionUID = -5441592609932865516L;
	public String JSESSIONID;
	public String id;
	
	public Double productWeight;
	
	public Double length;
	public Double width;
	public Double height;
	
	private String formsubmit;
	
	
	public String UserTempToken;
	public String sessionAuthenticationToken;
	
	
	public String execute()
	  {
		  
				Injector injector = Guice.createInjector(new GuiceModule());
				HibernateConnection connection = injector.getInstance(HibernateConnection.class);
				AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
				currentService.setConnection(connection);
				currentService.setInjector(injector);
		
				if(id == null)
				{
					return ERROR;
				}
				else
				{
					Product currentProduct = currentService.getProductById(Integer.parseInt(id));
					Integer categoryId = currentProduct.getCategoryid();
					ProductShipping productShipping = currentService.getProductShippingByProductId(Integer.parseInt(id));
					
						if(formsubmit != null)
						{
							Map<String, Integer> context = new HashMap<String, Integer>();
							context.put("categoryId", categoryId);
							
							ActionContext.getContext().getValueStack().push(context);
							
							if(productShipping == null)
							{
								
								productShipping = new ProductShipping();
								productShipping.setProductid(Integer.parseInt(id));
								productShipping.setWeight(productWeight);
								productShipping.setHeight(height);
								productShipping.setLength(length);
								productShipping.setWidth(width);
								currentService.addProductShipping(productShipping);
								
							
								return "submitted";
								
							}
							else
							{
								
								productShipping.setProductid(Integer.parseInt(id));
								productShipping.setWeight(productWeight);
								productShipping.setHeight(height);
								productShipping.setLength(length);
								productShipping.setWidth(width);
								currentService.updateProductShipping(productShipping);
								
								return "submitted";
							}
						}
						else //else the form hasnt been submitted, try and load previously submitted shipping information
						{
							if(productShipping != null)
							{
								
									productWeight = productShipping.getWeight();
									height = productShipping.getHeight();
									length = productShipping.getLength();
									width = productShipping.getWidth();
							}
						}
					}
				
			return SUCCESS;
		
	  }
	  public String getId() {
			return id;
		}
		public void setId(String id) 
		{
			this.id = id;
		}
	    @SuppressWarnings("unchecked")
		public String logout() throws Exception 
	    {
	    	Map session = ActionContext.getContext().getSession();
	    	session.remove("logged-in");
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
	    public String getJSESSIONID() 
	    {
			return JSESSIONID;
		}
		public void setJSESSIONID(String jsessionid) 
		{
			JSESSIONID = jsessionid;
		}
		
		public String getFormsubmit() 
		{
			return formsubmit;
		}
		public void setFormsubmit(String formsubmit) 
		{
			this.formsubmit = formsubmit;
		}
		public Double getProductWeight() 
		{
			return productWeight;
		}
		public void setProductWeight(Double productWeight) 
		{
			this.productWeight = productWeight;
		}
		public Double getLength() 
		{
			return length;
		}
		public void setLength(Double length) 
		{
			this.length = length;
		}
		public Double getWidth() 
		{
			return width;
		}
		public void setWidth(Double width) 
		{
			this.width = width;
		}
		public Double getHeight() 
		{
			return height;
		}
		public void setHeight(Double height) 
		{
			this.height = height;
		}
		
		
	}