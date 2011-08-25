package org.webhop.ywdc.extras;

public class ShippingChange 
{
	public Integer id;
	public String change;

	public ShippingChange(Integer id, String change)
	{
		setId(id);
		setChange(change);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}
	
}
