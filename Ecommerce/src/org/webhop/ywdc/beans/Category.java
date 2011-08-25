package org.webhop.ywdc.beans;

public class Category 
{
	public int id;
	public String name;
	public String description;
	public int parentid;
	
	
	public Category(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public int getParentid() 
	{
		return parentid;
	}
	public void setParentid(int parentid) 
	{
		this.parentid = parentid;
	}

	
}
