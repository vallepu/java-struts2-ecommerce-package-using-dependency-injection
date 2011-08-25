package org.webhop.ywdc.paypal.sdk;

import java.io.IOException;
import java.util.Properties;

public class ProfileConfiguration 
{
	public String username;
	public String password;
	public String signature;
	public String environment;
	
	public ProfileConfiguration()
	{
		Properties configFile = new Properties();
		   
		try 
		{
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("paypalconfig.properties"));
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		signature =  configFile.getProperty("signature"); 
		username =  configFile.getProperty("username"); 
		password =  configFile.getProperty("password"); 
		environment =  configFile.getProperty("environment"); 
		
		
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
