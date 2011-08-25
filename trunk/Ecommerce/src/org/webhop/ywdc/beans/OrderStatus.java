package org.webhop.ywdc.beans;

import java.sql.Timestamp;


public class OrderStatus 
{
	public Integer id;
	public String orderid;
	public Timestamp statusdate;
	public String status;

	public OrderStatus(){}
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(Timestamp statusdate) {
		this.statusdate = statusdate;
	}
}
