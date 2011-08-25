package org.webhop.ywdc.admin;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.ProductArchive;
import org.webhop.ywdc.dtos.PublicProduct;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class ProductInformation extends ActionSupport 
{
	public PublicProduct publicProduct;
	public ProductArchive productArchive;
	
	public Category category;
	

	public Integer id;
	
	private static final long serialVersionUID = 4676160407984719217L;

	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		
		
		publicProduct = currentService.getPublicProductById(id);
		if(publicProduct == null)
		{
			
			productArchive = currentService.getProductArchiveByProductId(id);
			category = new Category();
			category.setId(1);
			category.setName(productArchive.getCategoryname());
			category.setDescription(productArchive.getCategoryname());
			category.setParentid(1);
			
		}
		else
		{
			category = currentService.getCategoryById(publicProduct.getCategoryid());
		}
		
		
		
		return SUCCESS;
	}
	public PublicProduct getPublicProduct() {
		return publicProduct;
	}

	public void setPublicProduct(PublicProduct publicProduct) {
		this.publicProduct = publicProduct;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public ProductArchive getProductArchive() {
		return productArchive;
	}
	public void setProductArchive(ProductArchive productArchive) {
		this.productArchive = productArchive;
	}
}
