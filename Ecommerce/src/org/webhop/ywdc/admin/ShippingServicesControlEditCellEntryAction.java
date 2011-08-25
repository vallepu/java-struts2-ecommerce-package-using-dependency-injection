package org.webhop.ywdc.admin;

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
public class ShippingServicesControlEditCellEntryAction extends ActionSupport 
{
	private String id;
	private String servicename;
	private String carrierid;
	public String UserTempToken;
	private String servicecode;
	private String oper;
	
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
		
		
		//Integer carrierId = currentShippingService.getCarrierid();
		
		
		if(oper.compareTo("edit") == 0)
		{
			if(servicename != null)
			{
				ShippingServices currentShippingService = currentService.getShippingServiceById(Integer.parseInt(id));
				currentShippingService.setServicename(servicename);
				currentService.updateShippingService(currentShippingService);
			}
			if(servicecode != null)
			{
				ShippingServices currentShippingService = currentService.getShippingServiceById(Integer.parseInt(id));
				currentShippingService.setServicecode(servicecode);//setCarrier(carrier);
				currentService.updateShippingService(currentShippingService);
			}
			
		}
		if(oper.compareTo("add") == 0)
		{
			ShippingServices newshippingService = new ShippingServices();
			newshippingService.setServicecode(servicecode);
			newshippingService.setServicename(servicename);
			newshippingService.setCarrierid(Integer.parseInt(carrierid));
			
			currentService.addShippingService(newshippingService);
		}
		if(oper.compareTo("del") == 0)
		{
			ShippingServices currentshippingService = currentService.getShippingServiceById(Integer.parseInt(id));
			currentService.deleteShippingService(currentshippingService);
			
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
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getServicecode() {
		return servicecode;
	}
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	public String getOper() 
	{
		return oper;
	}
	public void setOper(String oper) 
	{
		this.oper = oper;
	}
	public String getCarrierid() {
		return carrierid;
	}
	public void setCarrierid(String carrierid) {
		this.carrierid = carrierid;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
}
