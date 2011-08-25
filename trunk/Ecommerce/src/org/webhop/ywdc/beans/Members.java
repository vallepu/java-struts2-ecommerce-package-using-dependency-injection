package org.webhop.ywdc.beans;

import java.io.Serializable;



public class Members implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	public int id;
	//public Member member;
	
	public String username;
	public String password;
	
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	/*
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
	
		this.member = member;
	}
	*/
	
}
