package org.webhop.ywdc.admin;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

	
public class JsonSelectAction extends ActionSupport 
{
	public Map<String, String> categories;
	public String UserTempToken;
	
	public String execute()
	{
		categories.put("test", "testing");
		return SUCCESS;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
}
