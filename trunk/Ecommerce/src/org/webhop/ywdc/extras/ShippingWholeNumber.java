package org.webhop.ywdc.extras;

public class ShippingWholeNumber 
{
	public Integer id;
	public String wholeNumber;
	
	public ShippingWholeNumber(Integer id, String wholeNumber)
	{
		setId(id);
		setWholeNumber(wholeNumber);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWholeNumber() {
		return wholeNumber;
	}

	public void setWholeNumber(String wholeNumber) {
		this.wholeNumber = wholeNumber;
	}

}
