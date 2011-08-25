package org.webhop.ywdc.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.CategoryRepository;
import org.webhop.ywdc.beans.Product;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



public class JsonInventoryControlAction extends ActionSupport 
{
	public String category;
	public String searchField;
	public String searchString;
	public Integer categoryId;
	public Integer rows	= 0;
	public Integer page	= 0;
	public Integer total;
	public Integer records = 0;
	public List<Product> gridModel;
	public String UserTempToken;
	
	private static final long serialVersionUID = -1958305015961075631L;
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		CategoryRepository categoryRepository = injector.getInstance(CategoryRepository.class);
		categoryRepository.setConnection(connection);
		
		
		
		
		
		if(category != null)
		{
			categoryId = Integer.parseInt(category);
			Integer firstResult = ((page - 1)*rows) + 1;
			//int to = (rows * page);
			//int from = to - rows;
			records = currentService.countTotalProductsByCategoryId(categoryId);
			//total = records/rows;
			total =(int) Math.ceil((double)records / (double)rows);

		//	gridModel = currentService.getProductByCategoryId(categoryId);
			gridModel = currentService.getProductByCategoryId(categoryId, firstResult, rows);
			
		}
		
		if(searchString != null)
		{
			if(searchField != null)
			{
				if(searchField.compareTo("name") == 0)
				{
					gridModel = currentService.getProductByProductCategoryAndNameSearch(categoryId, searchString);//getProductByProductNameSearch(searchField);
				}
				if(searchField.compareTo("summary") == 0)
				{
					gridModel = currentService.getProductByProductCategoryAndSummarySearch(categoryId, searchString);
				}
				if(searchField.compareTo("description") == 0)
				{
					gridModel = currentService.getProductByProductCategoryAndDescriptionSearch(categoryId, searchString);
				}
				if(searchField.compareTo("quantity") == 0)
				{
					gridModel = currentService.getProductByProductCategoryAndQuantitySearch(categoryId, Integer.parseInt(searchString));
				}
				if(searchField.compareTo("price") == 0)
				{
					Double thePrice = Double.parseDouble(searchString);
					gridModel = currentService.getProductByProductCategoryAndPriceSearch(categoryId, thePrice);
				}
			}
		}
		return SUCCESS;
	}
	public List<Product> getGridModel()
	{
		return this.gridModel;
	}
	public void setGridModel(List<Product> gridModel)
	{
		this.gridModel = gridModel;
	}
	public void setSearchField(String searchField)
	{
		this.searchField = searchField;
	}
	public String getSearchField(String searchField)
	{
		return this.searchField;
	}
	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}
	public String getSearchString()
	{
		return this.searchString;
	}
	public String getCategory()
	{
		return this.category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
}
