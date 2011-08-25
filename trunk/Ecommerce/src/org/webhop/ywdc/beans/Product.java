package org.webhop.ywdc.beans;

public class Product 
{
	public int id;
	public int categoryid;
	public String name;
	public int quantity;
	public Double price;
	public String summary;
	public String description;
	public int imagesid;

	public Product(){}

	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public int getCategoryid() 
	{
		return categoryid;
	}
	public void setCategoryid(int categoryid) 
	{
		this.categoryid = categoryid;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public int getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	public Double getPrice() 
	{
		return price;
	}
	public void setPrice(Double price) 
	{
		this.price = price;
	}
	public String getSummary() 
	{
		return summary;
	}
	public void setSummary(String summary) 
	{
		this.summary = summary;
	}
	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public int getImagesid() 
	{
		return imagesid;
	}

	public void setImagesid(int imagesid) 
	{
		this.imagesid = imagesid;
	}
	
}
