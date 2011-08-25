package org.webhop.ywdc.admin;

import java.util.List;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.CategoryRepository;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.opensymphony.xwork2.ActionSupport;

public class JsonCategoryControlAction extends ActionSupport
{
	public List<Category> gridModel;
	private static final long serialVersionUID = 4925070845783851072L;
	public String UserTempToken;

	
	public Integer rows	= 0;
	public Integer page	= 0;
	public Integer total;
	public Integer records = 0;
	
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices currentService = injector.getInstance(AuthenticationServices.class);
		currentService.setConnection(connection);
		currentService.setInjector(injector);
		
		 Integer firstResult = ((page - 1)*rows) + 1;
		records = currentService.countTotalCategories();
		total = (int) Math.ceil((double) records/ (double)rows);
		gridModel = currentService.getCategories(firstResult, rows);
		
		
		/*
		 
		 Integer firstResult = ((page - 1)*rows) + 1;
			//int to = (rows * page);
			//int from = to - rows;
			records = currentService.countTotalProductsByCategoryId(categoryId);
			//total = records/rows;
			total =(int) Math.ceil((double)records / (double)rows);

		//	gridModel = currentService.getProductByCategoryId(categoryId);
			gridModel = currentService.getProductByCategoryId(categoryId, firstResult, rows);
		 
		 
		 */
		
		
		
		//gridModel = currentService.getCategories();
		
		
		return SUCCESS;
	}
	public void setGridModel(List<Category> gridModel)
	{
		this.gridModel = gridModel;
	}
	public List<Category> getGridModel()
	{
		return this.gridModel;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
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
}
