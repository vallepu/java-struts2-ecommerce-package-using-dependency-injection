package org.webhop.ywdc.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.webhop.ywdc.beans.MembersRepository;
import org.webhop.ywdc.client.CategoryBuilder;
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
	
		/*
		List<Category> catList = aServices.getCategories();
		CategoryBuilder catBuilder = new CategoryBuilder();
		catBuilder.setCategories(catList);
		String wrapString = catBuilder.wrappingFunction();
		System.out.println(wrapString);
		*/
		
		//List<PublicProduct> pubprodList = aServices.getPublicProductByCategoryId(2);
		//for(PublicProduct p: pubprodList)
		//{
		//	System.out.println(p.getName());
		//}
		
	
	

	}

}