package org.webhop.ywdc.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.webhop.ywdc.client.CategoryBuilder;
import org.webhop.ywdc.client.Pagination;
import org.webhop.ywdc.dtos.PublicProduct;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;
import org.webhop.ywdc.util.RandomString;


import com.google.inject.Guice;
import com.google.inject.Injector;




public class Main {

	/**
	 * @param args
	 */
	public static MembersRepository numberRespository;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);

		
		AuthenticationServices aServices = injector.getInstance(AuthenticationServices.class);
		aServices.setConnection(connection);
		aServices.setInjector(injector);
	
		List<OrderStatus> orderstatusList = aServices.getIncompleteOrders();
		for(OrderStatus oS: orderstatusList)
		{
			System.out.println("OrderId: " + oS.getOrderid() + "Status: " + oS.getStatus());
			Timestamp orderdate = oS.getStatusdate();
			Integer OrderYear = orderdate.getYear();
			Integer OrderMonth = orderdate.getMonth();
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(orderdate);
			
			
			System.out.println("TIMESTAMP-OrderYear: " + calendar.get(Calendar.YEAR) + ", OrderMonth: " + calendar.get(Calendar.MONTH));
		}
	

	}

}