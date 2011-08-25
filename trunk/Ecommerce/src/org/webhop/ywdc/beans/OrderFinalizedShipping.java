package org.webhop.ywdc.beans;

public class OrderFinalizedShipping 
{
	public Integer id;
	public String orderid;
	public Double finalshippingcost;
	
	public OrderFinalizedShipping()
	{
		
	}
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

	public Double getFinalshippingcost() {
		return finalshippingcost;
	}

	public void setFinalshippingcost(Double finalshippingcost) {
		this.finalshippingcost = finalshippingcost;
	}

}
