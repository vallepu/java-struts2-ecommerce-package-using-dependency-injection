package org.webhop.ywdc.admin;

import java.util.List;
import java.util.Map;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.Customers;
import org.webhop.ywdc.beans.Product;
import org.webhop.ywdc.beans.ShippingCarrier;
import org.webhop.ywdc.beans.ShippingServices;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class ShippingCarrierControlEditCellEntryAction extends ActionSupport 
{
	private String id;
	private String carrier;
	private String oper;
	public String UserTempToken;
	
	public String execute()
	{
		
	
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		
		Map requestParameters = ActionContext.getContext().getParameters();//getParameters();
		String[] operArray = (String[])requestParameters.get("oper");
		oper = operArray[0];
		
		if(oper.compareTo("edit") == 0)
		{
			if(carrier != null)
			{
				ShippingCarrier currentCarrier = currentService.getShippingCarrierById(Integer.parseInt(id));//getCategoryById(Integer.parseInt(id));
				currentCarrier.setCarrier(carrier);
				currentService.updateShippingCarrier(currentCarrier);
			}
			
		}
		if(oper.compareTo("add") == 0)
		{
			ShippingCarrier newCarrier = new ShippingCarrier();
			newCarrier.setCarrier(carrier);
			
			currentService.addShippingCarrier(newCarrier);
		}
		if(oper.compareTo("del") == 0)
		{
			ShippingCarrier currentCarrier = currentService.getShippingCarrierById(Integer.parseInt(id));
			List<ShippingServices>shippingservicesList = currentService.getShippingServicesByCarrierId(Integer.parseInt(id));
			currentService.deleteShippingCarrier(currentCarrier);
			currentService.deleteShippingServices(shippingservicesList);
			//currentService.deleteShippingServicesByShippingCarrierId(Integer.parseInt(id));
			//must fill out service method right above
		}
		return SUCCESS;
		
	}
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getOper() 
	{
		return oper;
	}
	public void setOper(String oper) 
	{
		this.oper = oper;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
}
