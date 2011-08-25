package org.webhop.ywdc;

import java.util.ArrayList;
import java.util.List;

public class CustomerFactory 
{
	
	public CustomerFactory(){}
	
	public List<Customer> getCustomers()
	{
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(22, "Jason Vevoda"));
		customerList.add(new Customer(282, "Jim Bond"));
		
		return customerList;
	}
}
