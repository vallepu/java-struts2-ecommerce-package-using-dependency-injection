package org.webhop.ywdc.admin;

import java.util.List;

import org.webhop.ywdc.beans.ShippingCarrier;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class ShippingServicesToolAction extends ActionSupport 
{
	public Integer id;
	public String UserTempToken;
	
	public List<ShippingCarrier>shippingcarrierList;
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices ecommerceServices = injector.getInstance(AuthenticationServices.class);
		ecommerceServices.setConnection(connection);
		ecommerceServices.setInjector(injector);
		
		
		shippingcarrierList = ecommerceServices.getShippingCarriers();
		
		
		return SUCCESS;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}

	
}
