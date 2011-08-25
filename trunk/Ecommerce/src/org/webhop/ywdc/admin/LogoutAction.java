package org.webhop.ywdc.admin;

import java.util.Map;

import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport 
{
	  public String execute() throws Exception 
	  { 
		    Map session = ActionContext.getContext().getSession();
		    session.clear();
		    return SUCCESS;
	  }
}
