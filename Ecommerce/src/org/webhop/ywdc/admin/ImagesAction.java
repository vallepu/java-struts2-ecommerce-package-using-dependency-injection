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
public class ImagesAction extends ActionSupport {

	
	private static final long serialVersionUID = -5441592609932865516L;
	public String JSESSIONID;
	public String id;
	
	private String front;
	private String imagesid;
	private String formsubmit;
	
	private String back;
	private String sidea;
	private String sideb;
	
	private String imagetop;
	
	private String bottom;
	public Members member;
	public String UserTempToken;
	public String sessionAuthenticationToken;
	
	  @SuppressWarnings("unchecked")
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
					
					
					

					//need to send this category id back with the response so that when the page reloads if will open up the category they were on
					
					Integer imagesidInteger =  currentProduct.getImagesid();
					imagesid = imagesidInteger.toString();
			
					
						if(formsubmit != null)
						{
							Map<String, Integer> context = new HashMap<String, Integer>();
							context.put("categoryId", categoryId);
							
							ActionContext.getContext().getValueStack().push(context);
							
							if(imagesid.compareTo("0") == 0)
							{
								Images newImage =  new Images();
								newImage.setBack(back);
								newImage.setFront(front);
								newImage.setSidea(sidea);
								newImage.setSideb(sideb);
								newImage.setTop(imagetop);
								newImage.setBottom(bottom);
								int newimageId = currentService.addProductsImage(newImage);
								currentProduct.setImagesid(newimageId);
								currentService.updateProduct(currentProduct);
								return "submitted";
								//need to get the id of the product we just added, back
							}
							else
							{
								Images currentImage = currentService.getImagesByImageId(Integer.parseInt(imagesid));
								currentImage.setBack(back);
								currentImage.setFront(front);
								currentImage.setSidea(sidea);
								currentImage.setSideb(sideb);
								currentImage.setTop(imagetop);
								currentImage.setBottom(bottom);
								currentService.updateProductImages(currentImage);
								return "submitted";
							}
						}
						else
						{
							if(imagesid != null)
							{
								Images productImage = currentService.getImagesByImageId(Integer.parseInt(imagesid));
								if(productImage != null)
								{
									back = productImage.getBack();
									front = productImage.getFront();
									sidea = productImage.getSidea();
									sideb = productImage.getSideb();
									imagetop = productImage.getTop();
									bottom = productImage.getBottom();
								}
							}
						}
					}//then there is no image info in the database and you should load the page without looking to fill txtboxes
				
				
			return SUCCESS;
		
	  }
	  public String getId() {
			return id;
		}
		public void setId(String id) 
		{
			this.id = id;
		}
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
		public String getFront() {
			return front;
		}
		public void setFront(String front) {
			this.front = front;
		}
		public String getBack() {
			return back;
		}
		public void setBack(String back) {
			this.back = back;
		}
		public String getSidea() {
			return sidea;
		}
		public void setSidea(String sidea) {
			this.sidea = sidea;
		}
		public String getSideb() {
			return sideb;
		}
		public void setSideb(String sideb) {
			this.sideb = sideb;
		}
		
		public String getBottom() {
			return bottom;
		}
		public void setBottom(String bottom) {
			this.bottom = bottom;
		}
		public String getImagesid() {
			return imagesid;
		}
		public void setImagesid(String imagesid) {
			this.imagesid = imagesid;
		}
		public String getFormsubmit() {
			return formsubmit;
		}
		public void setFormsubmit(String formsubmit) {
			this.formsubmit = formsubmit;
		}
		public String getImagetop() {
			return imagetop;
		}
		public void setImagetop(String imagetop) {
			this.imagetop = imagetop;
		}
		
		
	}