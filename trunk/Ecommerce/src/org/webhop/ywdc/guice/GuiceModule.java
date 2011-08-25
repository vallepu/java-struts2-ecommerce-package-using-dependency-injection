package org.webhop.ywdc.guice;

import org.webhop.ywdc.beans.CategoryRepository;
import org.webhop.ywdc.beans.CustomersRepository;
import org.webhop.ywdc.beans.ImagesRepository;
import org.webhop.ywdc.beans.MembersRepository;
import org.webhop.ywdc.beans.OrderCarrierRepository;
import org.webhop.ywdc.beans.OrderFinalizedShippingRepository;
import org.webhop.ywdc.beans.OrderHistoryRepository;
import org.webhop.ywdc.beans.OrderItemsRepository;
import org.webhop.ywdc.beans.OrderPaymentRepository;
import org.webhop.ywdc.beans.OrderShippingRepository;
import org.webhop.ywdc.beans.OrderStatusRepository;
import org.webhop.ywdc.beans.ProductRepository;
import org.webhop.ywdc.beans.ProductShippingRepository;
import org.webhop.ywdc.beans.ShippingCarrierRepository;
import org.webhop.ywdc.beans.ShippingServicesRepository;

import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.ConnectionProvider;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.AbstractModule;

import com.google.inject.Scopes;
import com.google.inject.Singleton;


public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() 
	{
		bind(AuthenticationServices.class).in(Singleton.class);
		bind(HibernateConnection.class).toProvider(ConnectionProvider.class).in(Scopes.SINGLETON);
		//bind(ManagerService.class).to(ManagerServiceImpl.class).in(Singleton.class);
		bind(MembersRepository.class).in(Singleton.class);
		bind(ImagesRepository.class).in(Singleton.class);
		bind(ProductRepository.class).in(Singleton.class);
		bind(CustomersRepository.class).in(Singleton.class);
		bind(CategoryRepository.class).in(Singleton.class);
		bind(ProductShippingRepository.class).in(Singleton.class);
		bind(ShippingCarrierRepository.class).in(Singleton.class);
		bind(ShippingServicesRepository.class).in(Singleton.class);
		
		bind(OrderShippingRepository.class).in(Singleton.class);
		bind(OrderCarrierRepository.class).in(Singleton.class);
		bind(OrderHistoryRepository.class).in(Singleton.class);
		bind(OrderItemsRepository.class).in(Singleton.class);
		bind(OrderPaymentRepository.class).in(Singleton.class);
		bind(OrderStatusRepository.class).in(Singleton.class);
		bind(OrderFinalizedShippingRepository.class).in(Singleton.class);
	
	}

}
