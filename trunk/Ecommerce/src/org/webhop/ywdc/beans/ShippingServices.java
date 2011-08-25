package org.webhop.ywdc.beans;

public class ShippingServices 
{
	public Integer id;
	public String servicename;
	public Integer carrierid;
	public String servicecode;

	public ShippingServices()
	{
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public Integer getCarrierid() {
		return carrierid;
	}
	public void setCarrierid(Integer carrierid) {
		this.carrierid = carrierid;
	}
	public String getServicecode() {
		return servicecode;
	}
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	
}
