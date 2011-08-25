package org.webhop.ywdc.admin;

import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class ShippingCarrierToolAction extends ActionSupport 
{
	public String UserTempToken;
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices ecommerceServices = injector.getInstance(AuthenticationServices.class);
		ecommerceServices.setConnection(connection);
		ecommerceServices.setInjector(injector);
		
		//ecommerceServices.getShippingServices();
		
		
		return SUCCESS;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
}
