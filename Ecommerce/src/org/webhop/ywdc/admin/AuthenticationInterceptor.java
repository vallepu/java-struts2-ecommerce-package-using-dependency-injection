package org.webhop.ywdc.admin;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class AuthenticationInterceptor extends AbstractInterceptor
{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		
		//

		
		ActionContext actionContext = (ActionContext) invocation.getInvocationContext();	
		Map session = actionContext.getSession();
		String sessionAuthenticationToken = (String) session.get("UserTempToken");//put(usernameString, member);
		
		ValueStack valuestack = actionContext.getValueStack();
		String authCookie = (String) valuestack.findValue("UserTempToken");
		
		if(sessionAuthenticationToken != null)
		{
			if(authCookie != null)
			{
				if(sessionAuthenticationToken.compareTo(authCookie) == 0)
				{
					return invocation.invoke();
					
				}
				
				
			}
			
			
			
			
		}
		return "authenticate";  
	}

}
