package org.webhop.ywdc.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webhop.ywdc.beans.Category;
import org.webhop.ywdc.beans.CategoryRepository;
import org.webhop.ywdc.beans.OrderPayment;
import org.webhop.ywdc.beans.OrderStatus;
import org.webhop.ywdc.beans.Product;
import org.webhop.ywdc.dtos.GeneralizedOrder;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



public class JsonGeneralOrderAction extends ActionSupport 
{
	private static final long serialVersionUID = -5150300959923609561L;
	public List<OrderStatus> orderstatusList;
	public List<GeneralizedOrder> gridModel;
	public String ordertype;
	public String datefrom;
	public String datefromto;
	public String UserTempToken;
	
	public String dateto;
	public Integer categoryId;
	public Integer rows	= 0;
	public Integer page	= 0;
	public Integer total;
	public Integer records = 0;
	
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance((HibernateConnection.class));
		AuthenticationServices service = injector.getInstance((AuthenticationServices.class));
		service.setConnection(connection);
		service.setInjector(injector);
		
		String[] datefromtoarray = datefromto.split("[/]");
		String datefrom = datefromtoarray[0];
		String dateto = datefromtoarray[1];
		
		String[] fromdatearray = datefrom.split("[.]");
		
		Date fromdate = new Date();
		fromdate.setMonth(Integer.parseInt(fromdatearray[0]) - 1);
		fromdate.setDate(Integer.parseInt(fromdatearray[1]));
		fromdate.setYear(Integer.parseInt(fromdatearray[2]) - 1900);
		fromdate.setHours(0);
		fromdate.setMinutes(1);
		String[] todatearray = dateto.split("[.]");
		
		Date todate = new Date();
		todate.setMonth(Integer.parseInt(todatearray[0]) - 1);
		todate.setDate(Integer.parseInt(todatearray[1]));
		todate.setYear(Integer.parseInt(todatearray[2]) - 1900);
		todate.setHours(23);
		todate.setMinutes(59);
		
		gridModel = new ArrayList<GeneralizedOrder>();
		
		records = service.countTotalOrdersWithinDateRange(fromdate, todate);
		
		Integer firstResult = ((page - 1)*rows) + 1;
		
		total =(int) Math.ceil((double)records / (double)rows);
		
	
		gridModel = service.getGeneralizedOrderWithinDateRange(fromdate, todate, firstResult, rows);
		
		
		
		return SUCCESS;
	}
	public List<OrderStatus> getOrderstatusList() {
		return orderstatusList;
	}
	public void setOrderstatusList(List<OrderStatus> orderstatusList) {
		this.orderstatusList = orderstatusList;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	public List<GeneralizedOrder> getGridModel() {
		return gridModel;
	}
	public void setGridModel(List<GeneralizedOrder> gridModel) {
		this.gridModel = gridModel;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public String getDateto() {
		return dateto;
	}
	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	public String getDatefromto() {
		return datefromto;
	}
	public void setDatefromto(String datefromto) {
		this.datefromto = datefromto;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
}
