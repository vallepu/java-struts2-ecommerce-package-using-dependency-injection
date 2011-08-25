package org.webhop.ywdc.admin;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.webhop.ywdc.beans.Members;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;
import org.webhop.ywdc.util.RandomString;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Secured
public class LoginAction extends ActionSupport {

	
	private static final long serialVersionUID = -5441592609932865516L;
	public String JSESSIONID;
	public int id;
	private String userId;
	private String password;
	public Members member;
	public String UserTempToken;
	public String sessionAuthenticationToken;
	
	public String test;
	
	  public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	@SuppressWarnings("unchecked")
	public String execute()
	  {
		  
				Injector injector = Guice.createInjector(new GuiceModule());
				HibernateConnection connection = injector.getInstance(HibernateConnection.class);
				AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
				currentService.setConnection(connection);
				currentService.setInjector(injector);
		
				
				
		//if(UserTempToken == null)
		//{
			
			if(userId != null || password != null)
			{
				member = currentService.getMemberByUsernamePassword(userId, password);
			
			
				if(member == null)
				{
					return ERROR;
				}
				else
				{
					RandomString rString = new RandomString(28);
					String testRandom = rString.getRandomString();
					System.out.println(testRandom);
					//String randomString = "hbkj4id84kd94lf93kd9k6";
					id = member.getId();
					Map session = ActionContext.getContext().getSession();
					session.put("UserTempToken", testRandom);
					
					
					Cookie cookie = new Cookie("UserTempToken", testRandom);
			  		cookie.setMaxAge(11645);
			  		HttpServletResponse response = ServletActionContext.getResponse();
			  		response.addCookie(cookie);
			  		
			  		Map sessionTest = ActionContext.getContext().getSession();
			  		test = (String) session.get("UserTempToken");
			  		
					return SUCCESS;
				}	
			}
			else //else no session/cookie match from interceptor and no username/password incoming from request, so send to Login.jsp
			{
				return ERROR;
			}
		//}
		
		//return ERROR;
		
	  }
	    public String logout() throws Exception 
	    {
	    	Map session = ActionContext.getContext().getSession();
	    	session.remove("logged-in");
	        return SUCCESS;
	    }
	    public String getPassword() 
	    {
	        return password;
	    }
	    public void setPassword(String password) 
	    {
	        this.password = password;
	    }
	    public String getUserTempToken()
	    {
	    	return UserTempToken;
	    }
	    public void setUserTempToken(String UserTempToken)
	    {
	    	this.UserTempToken = UserTempToken;
	    }
	    public String getUserId() 
	    {
	        return userId;
	    }
	    public void setUserId(String userId) 
	    {
	        this.userId = userId;
	    }
	    public String getJSESSIONID() 
	    {
			return JSESSIONID;
		}
		public void setJSESSIONID(String jsessionid) 
		{
			JSESSIONID = jsessionid;
		}
		
	}