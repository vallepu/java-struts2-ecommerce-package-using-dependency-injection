package org.webhop.ywdc.dtos;

public class PublicProduct 
{
	
	public int id;
	public int categoryid;
	public String name;
	public int quantity;
	public Double price;
	public String formattedprice;
	public String description;
	public String summary;
	public String front;
	public String back;
	public String top;
	public String bottom;
	public String sidea;
	public String sideb;

	public PublicProduct(){}

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
	public String getFront() 
	{
		return front;
	}
	public void setFront(String front) 
	{
		this.front = front;
	}
	public String getBack() 
	{
		return back;
	}
	public void setBack(String back) 
	{
		this.back = back;
	}
	public String getTop() 
	{
		return top;
	}
	public void setTop(String top) 
	{
		this.top = top;
	}
	public String getBottom() 
	{
		return bottom;
	}
	public void setBottom(String bottom) 
	{
		this.bottom = bottom;
	}
	public String getSidea() 
	{
		return sidea;
	}
	public void setSidea(String sidea) 
	{
		this.sidea = sidea;
	}
	public String getSideb() 
	{
		return sideb;
	}
	public void setSideb(String sideb) 
	{
		this.sideb = sideb;
	}
	public String getFormattedprice() {
		return formattedprice;
	}

	public void setFormattedprice(String formattedprice) {
		this.formattedprice = formattedprice;
	}
}
