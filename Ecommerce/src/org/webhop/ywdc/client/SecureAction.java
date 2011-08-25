package org.webhop.ywdc.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;


public class SecureAction extends ActionSupport 
{
	private static final long serialVersionUID = 1993968759158088595L;
	public String catbuilt;
	private Map<String, String> accordion;


	public Map<String, String> getAccordion() {
		return accordion;
	}
	public void setAccordion(Map<String, String> accordion) {
		this.accordion = accordion;
	}

	public List<Category> categories;
	
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
		
		  accordion = new HashMap<String, String>();
	        accordion.put("Section 1", "<image src='http://www.harlemfur.com/images/Dog_Olive.jpg'></a>");
	        accordion.put("Section 2", "Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.");
	        accordion.put("Section 3", "Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.");
	        accordion.put("Section 4", "Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est. Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.");
	      

		
		
		
		return SUCCESS;
		
	}
	public String getCatbuilt() {
		return catbuilt;
	}

	public void setCatbuilt(String catbuilt) {
		this.catbuilt = catbuilt;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	

}
