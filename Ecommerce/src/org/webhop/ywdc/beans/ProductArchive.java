package org.webhop.ywdc.beans;

import java.util.Date;

public class ProductArchive 
{
	public Integer id;
	

	public Integer productid;
	public String productname;
	public Double price;
	

	public String categoryname;
	public String summary;
	public Date datedeleted;
	
	public ProductArchive(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getDatedeleted() {
		return datedeleted;
	}

	public void setDatedeleted(Date datedeleted) {
		this.datedeleted = datedeleted;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
