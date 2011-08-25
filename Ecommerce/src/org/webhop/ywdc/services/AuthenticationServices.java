package org.webhop.ywdc.services;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.CategoryRepository;
import org.webhop.ywdc.beans.Customers;
import org.webhop.ywdc.beans.CustomersRepository;
import org.webhop.ywdc.beans.Images;
import org.webhop.ywdc.beans.ImagesRepository;
import org.webhop.ywdc.beans.Members;
import org.webhop.ywdc.beans.MembersRepository;
import org.webhop.ywdc.beans.OrderCarrier;
import org.webhop.ywdc.beans.OrderCarrierRepository;
import org.webhop.ywdc.beans.OrderFinalizedShipping;
import org.webhop.ywdc.beans.OrderFinalizedShippingRepository;
import org.webhop.ywdc.beans.OrderItems;
import org.webhop.ywdc.beans.OrderItemsRepository;
import org.webhop.ywdc.beans.OrderPayment;
import org.webhop.ywdc.beans.OrderPaymentRepository;
import org.webhop.ywdc.beans.OrderShipping;
import org.webhop.ywdc.beans.OrderShippingRepository;
import org.webhop.ywdc.beans.OrderHistory;
import org.webhop.ywdc.beans.OrderHistoryRepository;
import org.webhop.ywdc.beans.OrderStatus;
import org.webhop.ywdc.beans.OrderStatusRepository;
import org.webhop.ywdc.beans.Product;
import org.webhop.ywdc.beans.ProductArchive;
import org.webhop.ywdc.beans.ProductArchiveRepository;
import org.webhop.ywdc.beans.ProductRepository;
import org.webhop.ywdc.beans.ProductShipping;
import org.webhop.ywdc.beans.ProductShippingRepository;
import org.webhop.ywdc.beans.ShippingCarrier;
import org.webhop.ywdc.beans.ShippingCarrierRepository;
import org.webhop.ywdc.beans.ShippingServices;
import org.webhop.ywdc.beans.ShippingServicesRepository;
import org.webhop.ywdc.dtos.GeneralizedOrder;
import org.webhop.ywdc.dtos.PublicProduct;
import org.webhop.ywdc.util.HibernateConnection;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class AuthenticationServices 
{
	public HibernateConnection connection;

	public Injector injector;
	
	@Inject
	public AuthenticationServices(Injector injector, HibernateConnection connection)
	{
		setInjector(injector);
		setConnection(connection);
	
	}
	
	
	public void addProductShipping(ProductShipping productShipping)
	{
		ProductShippingRepository psRepository = injector.getInstance(ProductShippingRepository.class);
		psRepository.setConnection(connection);
		psRepository.save(productShipping);
		psRepository.closeSession();
		
	}
	public void addCategory(Category category)
	{
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		cRepository.save(category);
		cRepository.closeSession();
	}
	public void addCustomer(Customers customer)
	{
		CustomersRepository cRepository = injector.getInstance(CustomersRepository.class);
		cRepository.setConnection(connection);
		cRepository.save(customer);
		cRepository.closeSession();
	}
	public void addOrderHistory(OrderHistory orderHistory)
	{
		OrderHistoryRepository ohRepository = injector.getInstance(OrderHistoryRepository.class);
		ohRepository.setConnection(connection);
		ohRepository.save(orderHistory);
		ohRepository.closeSession();
	}
	public void addOrderStatus(OrderStatus orderStatus)
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		osRepository.save(orderStatus);
		osRepository.closeSession();
	}
	public void addOrderCarrier(OrderCarrier orderCarrier)
	{
		OrderCarrierRepository ocRepository = injector.getInstance(OrderCarrierRepository.class);
		ocRepository.setConnection(connection);
		ocRepository.save(orderCarrier);
		ocRepository.closeSession();
	}
	
	public void addOrderFinalizedShipping(OrderFinalizedShipping orderfinalizedShipping)
	{
		OrderFinalizedShippingRepository ofsRepository = injector.getInstance(OrderFinalizedShippingRepository.class);
		ofsRepository.setConnection(connection);
		ofsRepository.save(orderfinalizedShipping);
		ofsRepository.closeSession();
	}
	
	public void addOrderItems(List<OrderItems> orderitemsList)
	{
		OrderItemsRepository oiRepository = injector.getInstance(OrderItemsRepository.class);
		oiRepository.setConnection(connection);
		
		
		for(OrderItems oItem: orderitemsList)
		{
			oiRepository.save(oItem);
			
		}
		oiRepository.closeSession();
	}
	public void addOrderPayment(OrderPayment orderPayment)
	{
		OrderPaymentRepository opRepository = injector.getInstance(OrderPaymentRepository.class);
		opRepository.setConnection(connection);
		opRepository.save(orderPayment);
		opRepository.closeSession();
	}
	public void addOrderShipping(OrderShipping orderShipping)
	{
		OrderShippingRepository osRepository = injector.getInstance(OrderShippingRepository.class);
		osRepository.setConnection(connection);
		osRepository.save(orderShipping);
		osRepository.closeSession();
	}
	public void addProduct(Product product)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		pRepository.save(product);
		pRepository.closeSession();
	}
	public Integer addProductsImage(Images newImage) 
	{
		ImagesRepository iRepository = injector.getInstance(ImagesRepository.class);
		iRepository.setConnection(connection);
		iRepository.save(newImage);
		iRepository.closeSession();
		Integer imagesId = newImage.getId();
		return imagesId;
		
	}
	public void addShippingCarrier(ShippingCarrier carrier)
	{
		ShippingCarrierRepository cRepository = injector.getInstance(ShippingCarrierRepository.class);
		cRepository.setConnection(connection);
		cRepository.save(carrier);
		cRepository.closeSession();
	}
	public void addShippingService(ShippingServices shippingService)
	{
		ShippingServicesRepository ssRepository = injector.getInstance(ShippingServicesRepository.class);
		ssRepository.setConnection(connection);
		ssRepository.save(shippingService);
		ssRepository.closeSession();
	}

	
	
	public void changeOrderStatusToComplete(String OrderId)
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		OrderStatus orderStatus = osRepository.findInstanceByName("orderid", OrderId);
		orderStatus.setStatus("Complete");
		osRepository.save(orderStatus);
		osRepository.closeSession();
		
	}
	public void changeOrderStatusToIncomplete(String OrderId)
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		OrderStatus orderStatus = osRepository.findInstanceByName("orderid", OrderId);
		orderStatus.setStatus("Incomplete");
		osRepository.save(orderStatus);
		osRepository.closeSession();
		
	}
	public void changeOrderStatusToRefund(String orderId) 
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		OrderStatus orderStatus = osRepository.findInstanceByName("orderid", orderId);
		orderStatus.setStatus("Refund");
		osRepository.save(orderStatus);
		osRepository.closeSession();
	}
	
	public Integer countTotalCategories()
	{
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		List<Category> catList = cRepository.findAll();
		cRepository.closeSession();
		Integer categoryCount = catList.size();
		catList.clear();
		return categoryCount;
		
		
	}
	
	public Integer countTotalCompleteOrders() 
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		List<OrderStatus> orderstatusList =  osRepository.findListByName("status", "Complete");
		osRepository.closeSession();
		Integer completeorderCount = orderstatusList.size();
		
		orderstatusList.clear();
		return completeorderCount;
	}

	public Integer countTotalIncompleteOrders()
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		List<OrderStatus> orderstatusList =  osRepository.findListByName("status", "Incomplete");
		osRepository.closeSession();
		Integer incompleteorderCount = orderstatusList.size();
		
		orderstatusList.clear();
		return incompleteorderCount;
	}
	
	public Integer countTotalOrdersWithinDateRange(Date fromdate, Date todate) 
	{
		OrderPaymentRepository opRepository = injector.getInstance(OrderPaymentRepository.class);
		opRepository.setConnection(connection);
		List<OrderPayment> orderpaymentList = opRepository.getOrderPaymentsWithinDateRange(fromdate, todate);
		opRepository.closeSession();
		Integer listCount = orderpaymentList.size();
		orderpaymentList.clear();
		return listCount;
	}
	
	public Integer countTotalProductsByCategoryId(Integer categoryId)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Product> productList =  pRepository.findProductByCategoryId(categoryId);
		pRepository.closeSession();
		Integer productCount = productList.size();
		
		productList.clear();
		return productCount;
	}
	
	public Integer countTotalRefundedOrders() 
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		List<OrderStatus> orderstatusList =  osRepository.findListByName("status", "Refund");
		osRepository.closeSession();
		Integer refundorderCount = orderstatusList.size();
		
		orderstatusList.clear();
		return refundorderCount;
	}

	
	
	public void deleteCategory(Category category)
	{
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		cRepository.delete(category);
		cRepository.closeSession();
	}
	public void deleteCustomer(Customers customer)
	{
		CustomersRepository cRepository = injector.getInstance(CustomersRepository.class);
		cRepository.setConnection(connection);
		cRepository.delete(customer);
		cRepository.closeSession();
	}
	public void deleteProduct(Product product)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		
		ProductArchiveRepository paRepository = injector.getInstance(ProductArchiveRepository.class);
		paRepository.setConnection(connection);
		
		ImagesRepository iRepository = injector.getInstance(ImagesRepository.class);
		iRepository.setConnection(connection);
		
		ProductShippingRepository psRepository = injector.getInstance(ProductShippingRepository.class);
		psRepository.setConnection(connection);
		
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		
		Category category = cRepository.findById(product.getCategoryid(), true);
		
		
		ProductArchive tmpArchive = new ProductArchive();
		tmpArchive.setCategoryname(category.getName());
		tmpArchive.setPrice(product.getPrice());
		tmpArchive.setProductid(product.getId());
		tmpArchive.setProductname(product.getName());
		tmpArchive.setSummary(product.getSummary());
		
		
		
		Images productImages = iRepository.findById(product.getImagesid(), true);
		ProductShipping productShipping = psRepository.findInstanceByNamedInteger("productid", product.getId());
		
		if(productImages != null)
		{
			iRepository.delete(productImages);
		}
		if(productShipping != null)
		{
			psRepository.delete(productShipping);
			
			paRepository.save(tmpArchive);
		}
		
		pRepository.delete(product);
		
		pRepository.closeSession();
		paRepository.closeSession();
		iRepository.closeSession();
		psRepository.closeSession();
		cRepository.closeSession();
		
		
		
	}
	public void deleteProductsByCategoryId(Integer categoryId)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		
		ProductArchiveRepository paRepository = injector.getInstance(ProductArchiveRepository.class);
		paRepository.setConnection(connection);
		
		ImagesRepository iRepository = injector.getInstance(ImagesRepository.class);
		iRepository.setConnection(connection);
		
		ProductShippingRepository psRepository = injector.getInstance(ProductShippingRepository.class);
		psRepository.setConnection(connection);
		
		Category category = cRepository.findById(categoryId, true);
		List<Product> tmpproductList = pRepository.findProductByCategoryId(categoryId);
		
		//we must create an archive for each product were deleting
		for(Product p: tmpproductList)
		{
			
			ProductArchive tmpArchive = new ProductArchive();
			tmpArchive.setCategoryname(category.getName());
			tmpArchive.setPrice(p.getPrice());
			tmpArchive.setProductid(p.getId());
			tmpArchive.setProductname(p.getName());
			tmpArchive.setSummary(p.getSummary());
			
			
			
			Images productImages = iRepository.findById(p.getImagesid(), true);
			ProductShipping productShipping = psRepository.findInstanceByNamedInteger("productid", p.getId());
			
			if(productImages != null)
			{
				iRepository.delete(productImages);
			}
			if(productShipping != null)
			{
				psRepository.delete(productShipping);
				paRepository.save(tmpArchive);
				
			}
			pRepository.delete(p);
			
		}
		pRepository.closeSession();
		cRepository.closeSession();
		paRepository.closeSession();
		iRepository.closeSession();
		psRepository.closeSession();
		
		
	}
	
	
	public void deleteShippingCarrier(ShippingCarrier carrier)
	{
		ShippingCarrierRepository scRepository = injector.getInstance(ShippingCarrierRepository.class);
		scRepository.setConnection(connection);
		scRepository.delete(carrier);
		scRepository.closeSession();
	}
	public void deleteShippingService(ShippingServices shippingService)
	{
		ShippingServicesRepository ssRepository = injector.getInstance(ShippingServicesRepository.class);
		ssRepository.setConnection(connection);
		ssRepository.delete(shippingService);
		ssRepository.closeSession();
	}
	public void deleteShippingServices(List<ShippingServices> shippingservicesList) 
	{
		ShippingServicesRepository ssRepository = injector.getInstance(ShippingServicesRepository.class);
		ssRepository.setConnection(connection);
		for(ShippingServices sService: shippingservicesList)
		{
			ssRepository.delete(sService);
		}
		ssRepository.closeSession();
	}
	
	
	
	public List<Object> getAllProducts()
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Object> objectList = pRepository.getAllProducts();
		pRepository.closeSession();
		return objectList;
		
	}
	public List<Category> getCategories()
	{
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		List<Category> catList = cRepository.getAllCategories();
		cRepository.closeSession();
		return catList;
	}
	
	public List<Category> getCategories(Integer firstResult, Integer maxResult)
	{
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		
		List<Category> tmpcategoryList = cRepository.findPaginatedCategories(firstResult);
		cRepository.closeSession();
		List<Category> finalcategoryList = new ArrayList<Category>();
		
		Integer startingInteger = 0;
		Integer tmpcategoryCount = tmpcategoryList.size();
		//do a check to see if tmpproductList.size() > maxResult.
		//if its not larger than just return the tmpproductList and have the publicproductlist
		//built from that list. If it is larger then do the while(startingInteger != maxResult){...}
		if(tmpcategoryCount <= maxResult)
		{
			//then build finalproductList from the tmpproductList
			for(Category c: tmpcategoryList)
			{
				finalcategoryList.add(c);
			}
		}
		else
		{
			//then build the finalproductList from the maxresultproductList
			
			while(startingInteger != maxResult)
			{
				Category tmpCategory = tmpcategoryList.get(startingInteger);
				finalcategoryList.add(tmpCategory);
				startingInteger++;
			}
			
		}
		tmpcategoryList.clear();
		
	
		
		return finalcategoryList;
		
		
	}
	
	
	public Category getCategoryById(Integer id)
	{
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		Category category = cRepository.findInstanceByNamedInteger("id", id);
		cRepository.closeSession();
		return category;
	}
	public List<OrderStatus> getCompletedOrders()
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		List<OrderStatus> orderstatusList = osRepository.findListByName("status", "Complete");
		osRepository.closeSession();
		return orderstatusList;
	}
	public List<OrderStatus> getCompletedOrders(Integer firstResult, Integer maxResult) 
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		
		List<OrderStatus> tmporderstatusList = osRepository.findPaginatedCompletedOrders(firstResult);
		osRepository.closeSession();
		List<OrderStatus> finalorderstatusList = new ArrayList<OrderStatus>();
		
		Integer startingInteger = 0;
		Integer tmporderstatusCount = tmporderstatusList.size();
		//do a check to see if tmpproductList.size() > maxResult.
		//if its not larger than just return the tmpproductList and have the publicproductlist
		//built from that list. If it is larger then do the while(startingInteger != maxResult){...}
		if(tmporderstatusCount <= maxResult)
		{
			//then build finalproductList from the tmpproductList
			for(OrderStatus os: tmporderstatusList)
			{
				finalorderstatusList.add(os);
			}
		}
		else
		{
			//then build the finalproductList from the maxresultproductList
			
			while(startingInteger != maxResult)
			{
				OrderStatus tmporderStatus = tmporderstatusList.get(startingInteger);
				finalorderstatusList.add(tmporderStatus);
				startingInteger++;
			}
			
		}
		tmporderstatusList.clear();
		
	
		
		return finalorderstatusList;
	}
	public List<Customers> getCustomers()
	{
		CustomersRepository cRepository = injector.getInstance(CustomersRepository.class);
		cRepository.setConnection(connection);
		List<Customers> customerList = cRepository.findAll();
		cRepository.closeSession();
		return customerList;
	}
	
	public List<GeneralizedOrder> getGeneralizedOrdersWithinDateRange(Calendar begin, Calendar end)
	{
		
		
		List<GeneralizedOrder> generalizedOrders = new ArrayList<GeneralizedOrder>();
		
		return generalizedOrders;
		
	}
	
	public Images getImagesByImageId(Integer id)
	{
		ImagesRepository iRepository = injector.getInstance(ImagesRepository.class);
		iRepository.setConnection(connection);
		Images image = iRepository.findById(id, true);
		iRepository.closeSession();
		return image;
	}
	public List<OrderStatus> getIncompleteOrders()
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		List<OrderStatus> orderstatusList = osRepository.getIncompleteOrders();
		osRepository.closeSession();
		return orderstatusList;
		
	}
	public List<OrderStatus> getIncompletOrders(Integer firstResult, Integer maxResult) 
	{
		
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		
		List<OrderStatus> tmporderstatusList = osRepository.findPaginatedIncompleteOrders(firstResult);
		osRepository.closeSession();
		List<OrderStatus> finalorderstatusList = new ArrayList<OrderStatus>();
		
		Integer startingInteger = 0;
		Integer tmporderstatusCount = tmporderstatusList.size();
		//do a check to see if tmpproductList.size() > maxResult.
		//if its not larger than just return the tmpproductList and have the publicproductlist
		//built from that list. If it is larger then do the while(startingInteger != maxResult){...}
		if(tmporderstatusCount <= maxResult)
		{
			//then build finalproductList from the tmpproductList
			for(OrderStatus os: tmporderstatusList)
			{
				finalorderstatusList.add(os);
			}
		}
		else
		{
			//then build the finalproductList from the maxresultproductList
			
			while(startingInteger != maxResult)
			{
				OrderStatus tmporderStatus = tmporderstatusList.get(startingInteger);
				finalorderstatusList.add(tmporderStatus);
				startingInteger++;
			}
			
		}
		tmporderstatusList.clear();
		
	
		
		return finalorderstatusList;
		
		
	}
	public Members getMemberByUsernamePassword(String userName, String passWord)
	{
		MembersRepository mRepository = injector.getInstance(MembersRepository.class);
		mRepository.setConnection(connection);
		Members retrievedMember = mRepository.findbyUsernamePassword(userName, passWord);
		mRepository.closeSession();
		return  retrievedMember;
	}
	public OrderCarrier getOrderCarrierByOrderId(String OrderId)
	{
		OrderCarrierRepository ocRepository = injector.getInstance(OrderCarrierRepository.class);
		ocRepository.setConnection(connection);
		OrderCarrier orderCarrier = ocRepository.findInstanceByName("orderid", OrderId);
		ocRepository.closeSession();
		return orderCarrier;
	}
	
	public OrderFinalizedShipping getOrderFinalizedShippingByOrderId(String orderId)
	{
		OrderFinalizedShippingRepository ofRepository = injector.getInstance(OrderFinalizedShippingRepository.class);
		ofRepository.setConnection(connection);
		OrderFinalizedShipping ofShipping = ofRepository.findInstanceByName("orderid", orderId);
		ofRepository.closeSession();
		return ofShipping;
	}
	
	public List<OrderHistory> getOrderHistoryByOrderId(String OrderId)
	{
		OrderHistoryRepository ohRepository = injector.getInstance(OrderHistoryRepository.class);
		ohRepository.setConnection(connection);
		List<OrderHistory> orderstatusList = ohRepository.findListByName("orderid", OrderId);
		ohRepository.closeSession();
		return orderstatusList;
	}
	public List<OrderItems> getOrderItemsByOrderId(String OrderId)
	{
		OrderItemsRepository oiRepository = injector.getInstance(OrderItemsRepository.class);
		oiRepository.setConnection(connection);
		List<OrderItems> orderItems = oiRepository.findListByName("orderid", OrderId);
		oiRepository.closeSession();
		return orderItems;
	}
	
	public OrderPayment getOrderPaymentByOrderPaymentId(String orderpaymentId)
	{
		OrderPaymentRepository opRepository = injector.getInstance(OrderPaymentRepository.class);
		opRepository.setConnection(connection);
		OrderPayment orderPayment = opRepository.findById(Integer.parseInt(orderpaymentId), true);
		opRepository.closeSession();
		return orderPayment;
	}
	public OrderPayment getOrderPaymentByOrderId(String OrderId)
	{
		OrderPaymentRepository opRepository = injector.getInstance(OrderPaymentRepository.class);
		opRepository.setConnection(connection);
		OrderPayment orderPayment = opRepository.findInstanceByName("orderid", OrderId);
		opRepository.closeSession();
		return orderPayment;
	}
	public List<GeneralizedOrder> getGeneralizedOrderWithinDateRange(Date fromdate, Date todate, Integer firstResult, Integer maxResult) 
	{
		OrderPaymentRepository opRepository = injector.getInstance(OrderPaymentRepository.class);
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		opRepository.setConnection(connection);
		osRepository.setConnection(connection);
		List<OrderPayment> orderpaymentList = opRepository.findPaginatedOrderPaymentByDateRange(fromdate, todate, firstResult);
		
		List<GeneralizedOrder> genorderList = new ArrayList<GeneralizedOrder>();
		
		/////////////////////////////////////////////
		
		Integer startingInteger = 0;
		Integer tmporderpaymentCount = orderpaymentList.size();
		//do a check to see if tmpproductList.size() > maxResult.
		//if its not larger than just return the tmpproductList and have the publicproductlist
		//built from that list. If it is larger then do the while(startingInteger != maxResult){...}
		if(tmporderpaymentCount <= maxResult)
		{
			//then build finalproductList from the tmpproductList
			for(OrderPayment op: orderpaymentList)
			{
				
				GeneralizedOrder currentgenOrder = new GeneralizedOrder();
				OrderStatus currentorderpaymentsOrderStatus = osRepository.findInstanceByName("orderid", op.getOrderid());
				
				currentgenOrder.setFirstname(op.getFirstname());
				currentgenOrder.setLastname(op.getLastname());
				currentgenOrder.setId(op.getId());
				currentgenOrder.setCardtype(op.getCardtype());
				currentgenOrder.setOrderdate(op.getOrderdate());
				currentgenOrder.setStatus(currentorderpaymentsOrderStatus.getStatus());
				currentgenOrder.setOrderid(op.getOrderid());
				currentgenOrder.setTotal(op.getTotal());
				
				genorderList.add(currentgenOrder);
			}
		}
		else
		{
			//then build the finalproductList from the maxresultproductList
			
			while(startingInteger != maxResult)
			{
				OrderPayment op = orderpaymentList.get(startingInteger);
				
				GeneralizedOrder currentgenOrder = new GeneralizedOrder();
				OrderStatus currentorderpaymentsOrderStatus = osRepository.findInstanceByName("orderid", op.getOrderid());
				
				currentgenOrder.setFirstname(op.getFirstname());
				currentgenOrder.setLastname(op.getLastname());
				currentgenOrder.setId(op.getId());
				currentgenOrder.setCardtype(op.getCardtype());
				currentgenOrder.setOrderdate(op.getOrderdate());
				currentgenOrder.setStatus(currentorderpaymentsOrderStatus.getStatus());
				currentgenOrder.setOrderid(op.getOrderid());
				currentgenOrder.setTotal(op.getTotal());
				
				genorderList.add(currentgenOrder);
				
				
				
				startingInteger++;
			}
			
		}
		opRepository.closeSession();
		osRepository.closeSession();
		orderpaymentList.clear();
		
		return genorderList;
		
	}
	public OrderShipping getOrderShippingByOrderId(String OrderId)
	{
		OrderShippingRepository osRepository = injector.getInstance(OrderShippingRepository.class);
		osRepository.setConnection(connection);
		OrderShipping orderShipping = osRepository.findInstanceByName("orderid", OrderId);
		osRepository.closeSession();
		return orderShipping;
	}
	public OrderStatus getOrderStatusByOrderId(String OrderId)
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		OrderStatus orderStatus = osRepository.findInstanceByName("orderid", OrderId);
		osRepository.closeSession();
		return orderStatus;
	}
	public List<Product> getProductByCategoryId(Integer categoryId)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Product> productList = pRepository.findProductByCategoryId(categoryId);
		pRepository.closeSession();
		return productList;
	}

	
	
	public List<Product> getProductByCategoryId(Integer categoryId, Integer firstResult, Integer maxResult)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		
		List<Product> tmpproductList = pRepository.findPaginatedProductByCategoryId(categoryId, firstResult);
	
		pRepository.closeSession();
		
		List<Product> finalproductList = new ArrayList<Product>();
		
		Integer startingInteger = 0;
		Integer tmpproductCount = tmpproductList.size();
		//do a check to see if tmpproductList.size() > maxResult.
		//if its not larger than just return the tmpproductList and have the publicproductlist
		//built from that list. If it is larger then do the while(startingInteger != maxResult){...}
		if(tmpproductCount <= maxResult)
		{
			//then build finalproductList from the tmpproductList
			for(Product p: tmpproductList)
			{
				finalproductList.add(p);
			}
		}
		else
		{
			//then build the finalproductList from the maxresultproductList
			
			while(startingInteger != maxResult)
			{
				Product tmpProduct = tmpproductList.get(startingInteger);
				finalproductList.add(tmpProduct);
				startingInteger++;
			}
			
		}
		tmpproductList.clear();
		
	
		
		return finalproductList;
		
		
	}
	public Product getProductById(Integer id)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		Product product = pRepository.findById(id, true);
		pRepository.closeSession();
		return product;
	}
	public Product getProductByProductDesciptionSearch(String description)
	{
		
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		Product product = pRepository.findInstanceByName("description", description);
		pRepository.closeSession();
		return product;
	}
	public List<Product> getProductByProductCategoryAndDescriptionSearch(Integer categoryId, String description)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Product> productList = pRepository.findProductByCategoryAndDescription(categoryId, description);
		pRepository.closeSession();
		return productList;
	}
	public List<Product> getProductByProductCategoryAndNameSearch(Integer categoryId, String name)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Product> productList = pRepository.findProductByCategoryAndName(categoryId, name);
		pRepository.closeSession();
		return productList;
		
	}
	public List<Product> getProductByProductCategoryAndPriceSearch(Integer categoryId, Double price)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Product> productList = pRepository.findProductByCategoryAndPrice(categoryId, price);
		pRepository.closeSession();
		return productList;
	}
	public List<Product> getProductByProductCategoryAndQuantitySearch(Integer categoryId, Integer quantity)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Product> productList = pRepository.findProductByCategoryAndQuantity(categoryId, quantity);
		pRepository.closeSession();
		return productList;
	}
	public List<Product> getProductByProductCategoryAndSummarySearch(Integer categoryId, String summary) 
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Product> productList = pRepository.findProductByCategoryAndSummary(categoryId, summary);
		pRepository.closeSession();
		return productList;
		
	}
	public Product getProductByProductName(String name)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		Product product = pRepository.findInstanceByName("name", name);
		pRepository.closeSession();
		return product;
	}
	public Product getProductByProductNameSearch(String name)
	{
		
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		Product product = pRepository.findInstanceByName("name", name);
		pRepository.closeSession();
		return product;
	}
	public Product getProductByProductQuantitySearch(String name, String value)
	{
		
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		Product product = pRepository.findInstanceByName("name", name);
		pRepository.closeSession();
		return product;
	}
	public List<Product> getProducts()
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		List<Product> productList = pRepository.findAll();
		pRepository.closeSession();
		return productList;
	}
	public ProductShipping getProductShippingByProductId(Integer productId)
	{
		ProductShippingRepository psRepository = injector.getInstance(ProductShippingRepository.class);
		psRepository.setConnection(connection);
		
		ProductShipping productShipping = psRepository.findInstanceByNamedInteger("productid", productId);
		
		psRepository.closeSession();
		return productShipping;
		
	}
	
	public ProductArchive getProductArchiveByProductId(Integer id) 
	{
		ProductArchiveRepository paRepository = injector.getInstance(ProductArchiveRepository.class);
		
		paRepository.setConnection(connection);
		ProductArchive tmpProductArchive = paRepository.findInstanceByNamedInteger("productid", id);
		
		paRepository.closeSession();
		return tmpProductArchive;
	}

	
	public List<PublicProduct> getPublicProductByCategoryId(Integer categoryId, Integer firstResult, Integer maxResult)
	{
		List<PublicProduct> publicproductList = new ArrayList<PublicProduct>();
		
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		
		pRepository.setConnection(connection);
		
		List<Product> tmpproductList = pRepository.findPaginatedProductByCategoryId(categoryId, firstResult);
		pRepository.closeSession();
		List<Product> finalproductList = new ArrayList<Product>();
		
		Integer startingInteger = 0;
		Integer tmpproductCount = tmpproductList.size();
		//do a check to see if tmpproductList.size() > maxResult.
		//if its not larger than just return the tmpproductList and have the publicproductlist
		//built from that list. If it is larger then do the while(startingInteger != maxResult){...}
		if(tmpproductCount <= maxResult)
		{
			//then build finalproductList from the tmpproductList
			for(Product p: tmpproductList)
			{
				finalproductList.add(p);
			}
		}
		else
		{
			//then build the finalproductList from the maxresultproductList
			
			while(startingInteger != maxResult)
			{
				Product tmpProduct = tmpproductList.get(startingInteger);
				finalproductList.add(tmpProduct);
				startingInteger++;
			}
			
		}
		tmpproductList.clear();
		
	
		if(finalproductList != null)
		{
			for(Product p: finalproductList)
			{
				
				Integer imgId = p.getImagesid();
				ImagesRepository iRepository =  injector.getInstance(ImagesRepository.class);
				iRepository.setConnection(connection);
				Images image = iRepository.findById(imgId, true);
				PublicProduct pubProduct = new PublicProduct();
				pubProduct.setId(p.getId());
				pubProduct.setName(p.getName());
				pubProduct.setPrice(p.getPrice());
				pubProduct.setSummary(p.getSummary());
				NumberFormat twoDForm = NumberFormat.getCurrencyInstance(Locale.US);  
		
				String priceString = twoDForm.format(p.getPrice());
				pubProduct.setFormattedprice(priceString);
				
				pubProduct.setQuantity(p.getQuantity());
				
				pubProduct.setDescription(p.getDescription());
				pubProduct.setCategoryid(p.getCategoryid());
				if(image != null)
				{
					pubProduct.setBack(image.getBack());
					pubProduct.setBottom(image.getBottom());
					pubProduct.setFront(image.getFront());
					pubProduct.setSidea(image.getSidea());
					pubProduct.setSideb(image.getSideb());
					pubProduct.setTop(image.getTop());
				}
				publicproductList.add(pubProduct);
			}
		}
		return publicproductList;
		
		
	}
	public List<PublicProduct> getPublicProductByHashMap(HashMap cartMap)
	{
		List<PublicProduct> publicproductList = new ArrayList<PublicProduct>();
		
		
		 	Set set = cartMap.entrySet();

		    Iterator i = set.iterator();
		    ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		    ImagesRepository iRepository = injector.getInstance(ImagesRepository.class);
			iRepository.setConnection(connection);
		    pRepository.setConnection(connection);
		    
		    while(i.hasNext())
		    {
		    	Map.Entry me = (Map.Entry)i.next();
			    Integer productId = (Integer) me.getKey();
		    	Product p = pRepository.findById(productId, true);
		    	Integer imgId = p.getImagesid();
		    	
				iRepository.setConnection(connection);
				Images image = iRepository.findById(imgId, true);
				PublicProduct pubProduct = new PublicProduct();
				pubProduct.setId(p.getId());
				pubProduct.setName(p.getName());
				pubProduct.setPrice(p.getPrice());
				pubProduct.setQuantity(p.getQuantity());
				
				NumberFormat twoDForm = NumberFormat.getCurrencyInstance(Locale.US);  
				String priceString = twoDForm.format(p.getPrice());
				pubProduct.setFormattedprice(priceString);
				
				pubProduct.setDescription(p.getDescription());
				pubProduct.setCategoryid(p.getCategoryid());
				if(image != null)
				{
					pubProduct.setBack(image.getBack());
					pubProduct.setBottom(image.getBottom());
					pubProduct.setFront(image.getFront());
					pubProduct.setSidea(image.getSidea());
					pubProduct.setSideb(image.getSideb());
					pubProduct.setTop(image.getTop());
				}
				publicproductList.add(pubProduct);
		
		     
		      
		      
		    }
		    pRepository.closeSession();
		    iRepository.closeSession();
		    return publicproductList;
	}
	public PublicProduct getPublicProductById(Integer id)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		Product product = pRepository.findById(id, true);
		pRepository.closeSession();
		if(product != null)
		{
			Integer imageId = product.getImagesid();
			ImagesRepository iRepository = injector.getInstance(ImagesRepository.class);
			iRepository.setConnection(connection);
			Images image = iRepository.findById(imageId, true);
			PublicProduct publicProduct = new PublicProduct();
			if(image != null)
			{
				
				publicProduct.setBack(image.getBack());
				publicProduct.setBottom(image.getBottom());
				publicProduct.setFront(image.getFront());
				publicProduct.setTop(image.getTop());
				publicProduct.setSidea(image.getSidea());
				publicProduct.setSideb(image.getSideb());
			}
			NumberFormat twoDForm = NumberFormat.getCurrencyInstance(Locale.US);  
			
			String priceString = twoDForm.format(product.getPrice());
			publicProduct.setFormattedprice(priceString);
			publicProduct.setSummary(product.getSummary());
			publicProduct.setCategoryid(product.getCategoryid());
			publicProduct.setDescription(product.getDescription());
			publicProduct.setName(product.getName());
			publicProduct.setQuantity(product.getQuantity());
			publicProduct.setId(product.getId());
			publicProduct.setPrice(product.getPrice());
			return publicProduct;
		}
		else
		{
			PublicProduct pProduct = null;
			return pProduct;
		}
		
		
	}
	
	public List<OrderStatus> getRefundedOrders() 
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		List<OrderStatus> osList = osRepository.findListByName("status", "Refund");
		osRepository.closeSession();
		return osList;
	}
	public List<OrderStatus> getRefundedOrders(Integer firstResult, Integer maxResult) 
	{
		OrderStatusRepository osRepository = injector.getInstance(OrderStatusRepository.class);
		osRepository.setConnection(connection);
		
		List<OrderStatus> tmporderstatusList = osRepository.findPaginatedRefundedOrders(firstResult);
		osRepository.closeSession();
		List<OrderStatus> finalorderstatusList = new ArrayList<OrderStatus>();
		
		Integer startingInteger = 0;
		Integer tmporderstatusCount = tmporderstatusList.size();
		//do a check to see if tmpproductList.size() > maxResult.
		//if its not larger than just return the tmpproductList and have the publicproductlist
		//built from that list. If it is larger then do the while(startingInteger != maxResult){...}
		if(tmporderstatusCount <= maxResult)
		{
			//then build finalproductList from the tmpproductList
			for(OrderStatus os: tmporderstatusList)
			{
				finalorderstatusList.add(os);
			}
		}
		else
		{
			//then build the finalproductList from the maxresultproductList
			
			while(startingInteger != maxResult)
			{
				OrderStatus tmporderStatus = tmporderstatusList.get(startingInteger);
				finalorderstatusList.add(tmporderStatus);
				startingInteger++;
			}
			
		}
		tmporderstatusList.clear();
		
	
		
		return finalorderstatusList;
	}
	
	public ShippingCarrier getShippingCarrierById(Integer id)
	{
		ShippingCarrierRepository shippingcarrierRepository = injector.getInstance(ShippingCarrierRepository.class);
		shippingcarrierRepository.setConnection(connection);
		ShippingCarrier sCarrier = shippingcarrierRepository.findById(id, true);
		shippingcarrierRepository.closeSession();
		return sCarrier;
	}
	public List<ShippingCarrier> getShippingCarriers()
	{
		ShippingCarrierRepository shippingcarrierRepository = injector.getInstance(ShippingCarrierRepository.class);
		shippingcarrierRepository.setConnection(connection);
		List<ShippingCarrier> scarrierList = shippingcarrierRepository.findAll();
		shippingcarrierRepository.closeSession();
		return scarrierList;
	}
	public List<ShippingServices> getShippingServicesByCarrierId(Integer carrierId)
	{
		ShippingServicesRepository ssRepository = injector.getInstance((ShippingServicesRepository.class));
		ssRepository.setConnection(connection);
		List<ShippingServices> ssList = ssRepository.findListByNamedInteger("carrierid", carrierId);
		ssRepository.closeSession();
		return ssList;
	}
	public ShippingServices getShippingServiceById(Integer serviceId)
	{
		ShippingServicesRepository ssRepository = injector.getInstance((ShippingServicesRepository.class));
		ssRepository.setConnection(connection);
		ShippingServices shippingService = ssRepository.findById(serviceId, true);
		ssRepository.closeSession();
		return shippingService;
	}
	
	
	
	
	
	
	public void updateCategory(Category category)
	{
		
		CategoryRepository cRepository = injector.getInstance(CategoryRepository.class);
		cRepository.setConnection(connection);
		cRepository.update(category);
		cRepository.closeSession();
	}
	public void updateCustomer(Customers customer)
	{
		
		CustomersRepository cRepository = injector.getInstance(CustomersRepository.class);
		cRepository.setConnection(connection);
		cRepository.update(customer);
		cRepository.closeSession();
	}
	public void updateOrderPaymentTransactionId(String orderId, String transactionId) 
	{
		OrderPaymentRepository oRepository = injector.getInstance(OrderPaymentRepository.class);
		oRepository.setConnection(connection);
		OrderPayment oPayment = oRepository.findInstanceByName("orderid", orderId);
		oPayment.setTransactionid(transactionId);
		oRepository.update(oPayment);
		oRepository.closeSession();
		
	}
	public void updateProduct(Product product)
	{
		
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		pRepository.update(product);
		pRepository.closeSession();
	}
	public void updateProductByPublicProductList(List<PublicProduct> publicproductList)
	{
		ProductRepository pRepository = injector.getInstance(ProductRepository.class);
		pRepository.setConnection(connection);
		for(PublicProduct pProduct: publicproductList)
		{
			
			
			Product product = pRepository.findById(pProduct.getId(), true);
			product.setQuantity(pProduct.getQuantity());
			
			pRepository.update(product);
			
		}
		pRepository.closeSession();
		
		
	}
	public void updateProductImages(Images images)
	{
		
		ImagesRepository iRepository = injector.getInstance(ImagesRepository.class);
		iRepository.setConnection(connection);
		iRepository.update(images);
		iRepository.closeSession();
	}
	public void updateProductShipping(ProductShipping productShipping)
	{
		ProductShippingRepository psRepository = injector.getInstance(ProductShippingRepository.class);
		psRepository.setConnection(connection);
		psRepository.update(productShipping);
		psRepository.closeSession();
	}
	public void updateShippingCarrier(ShippingCarrier carrier)
	{
		
		ShippingCarrierRepository cRepository = injector.getInstance(ShippingCarrierRepository.class);
		cRepository.setConnection(connection);
		cRepository.update(carrier);
		cRepository.closeSession();
	}
	public void updateShippingService(ShippingServices shippingService)
	{
		
		ShippingServicesRepository ssRepository = injector.getInstance(ShippingServicesRepository.class);
		ssRepository.setConnection(connection);
		ssRepository.update(shippingService);
		ssRepository.closeSession();
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setConnection(HibernateConnection connection)
	{
		this.connection = connection;
	}
	public void setInjector(Injector injector)
	{
		this.injector = injector;
	}


	

	


	


	




	


	

	


	


	


	
	
	
	
	
}
