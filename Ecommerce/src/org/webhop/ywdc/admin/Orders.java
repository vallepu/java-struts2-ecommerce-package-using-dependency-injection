package org.webhop.ywdc.admin;

import java.util.List;

import org.webhop.ywdc.beans.OrderStatus;

import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class Orders extends ActionSupport 
{

	public List<OrderStatus> orderstatusList;
	
	private static final long serialVersionUID = 1919291390385028684L;

	public String execute()
	{
		return SUCCESS;
	}

}
