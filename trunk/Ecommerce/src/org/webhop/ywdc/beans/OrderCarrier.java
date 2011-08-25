package org.webhop.ywdc.beans;

public class OrderCarrier 
{
	public Integer id;
	public String orderid;
	public Integer serviceid;
	public Double totalshippingcost;
	
	public OrderCarrier(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getServiceid() {
		return serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public Double getTotalshippingcost() {
		return totalshippingcost;
	}

	public void setTotalshippingcost(Double totalshippingcost) {
		this.totalshippingcost = totalshippingcost;
	}


}
