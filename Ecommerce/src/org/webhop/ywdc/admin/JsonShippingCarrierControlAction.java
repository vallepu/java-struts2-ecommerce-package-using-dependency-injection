package org.webhop.ywdc.admin;

import java.util.List;


import org.webhop.ywdc.beans.ShippingCarrier;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.opensymphony.xwork2.ActionSupport;

public class JsonShippingCarrierControlAction extends ActionSupport
{
	public List<ShippingCarrier> gridModel;
	private static final long serialVersionUID = 4925170845783851072L;
	public String UserTempToken;

	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		gridModel = currentService.getShippingCarriers();
		
		return SUCCESS;
	}
	public List<ShippingCarrier> getGridModel() 
	{
		return gridModel;
	}
	public void setGridModel(List<ShippingCarrier> gridModel) 
	{
		this.gridModel = gridModel;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
}
