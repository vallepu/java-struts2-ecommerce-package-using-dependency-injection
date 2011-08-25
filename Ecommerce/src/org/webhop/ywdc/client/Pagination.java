package org.webhop.ywdc.client;

import java.util.List;

import org.webhop.ywdc.dtos.PublicProduct;

public class Pagination 
{
	public String paginatedlinkStructure;
	public Integer categoryId;
	public Integer productsperPage;
	public Integer currentPage;
	public Integer totalProducts;
	
	public Pagination()
	{
		
	}
	public String buildPaginatedLinkStructure()
	{
		//all the logic for building the paginated link structure should be in here/
		//should have, in the controller class already have set the categoryId and
		//the public product list by the time we get  here so that we can just build up the link structure..
		paginatedlinkStructure = "";
		String previousPage = "";
		String nextPage = "";
		String linkEntry = "<a href='./categoryproduct.action?";
		String linkClosing = "</a>";
		String categoryidString = "categoryId=" + Integer.toString(categoryId) + "&";
		//String totalproductsString = "&totalProducts=" + totalProducts;
		double totalProductsDouble = (double) totalProducts;
		double productsperPageDouble = (double) productsperPage;
		double unroundedtotalPagesDouble = totalProductsDouble/productsperPageDouble;
		double totalPagesDouble = Math.ceil(totalProductsDouble/productsperPageDouble);
		//int totalPages = Double.
		//String totalPagesString = Double.toString(totalPagesDouble);
		/*
		if(totalPagesDouble - unroundedtotalPagesDouble == 0)
		{
			totalPagesDouble = totalPagesDouble - 1;
		}
		else
		{
			totalPagesDouble = Math.ceil(totalProductsDouble/productsperPageDouble);
		}
		*/
		Integer totalPages = (int) totalPagesDouble;
		
		//	Integer totalPages = Integer.parseInt(totalPagesString);
		if(totalPages == 0)
		{
			totalPages = 1;
		}
		Integer countingtocurrentPage = 1;
		
		//need to create a previous page, next page link at the "beginning" and "ending" of the paginatedlinkStructure
		//(except for the css styling div tag which will prepend and end the string itself;
		if(totalPages != 1)
		{
			if(totalPages < 10)
			{
			
				while(countingtocurrentPage <= totalPages)
				{
					if(countingtocurrentPage == currentPage)
					{
						paginatedlinkStructure = paginatedlinkStructure + " " + Integer.toString(currentPage);
					}
					else
					{
						paginatedlinkStructure = paginatedlinkStructure + " " +  linkEntry + categoryidString + "currentPage=" + countingtocurrentPage + "'>" + countingtocurrentPage + linkClosing;
						
					}
					countingtocurrentPage ++;
				}
				//create a previous link page
				if(currentPage != 1 && totalPages != 1) //then create a previous page link
				{
					Integer previouspageNumber = currentPage - 1;
					previousPage = linkEntry + categoryidString + "currentPage=" + previouspageNumber + "'>" + " Previous Page " + linkClosing;
				}
				
				//create a  next page if the current page is not the last page
				if(currentPage + 1 != countingtocurrentPage)
				{
					countingtocurrentPage = currentPage + 1;
					nextPage = linkEntry + categoryidString + "currentPage=" + countingtocurrentPage + "'>" + " Next Page " + linkClosing;
				
				}
				
				paginatedlinkStructure = "<div class='pagination'>" +  previousPage + paginatedlinkStructure + nextPage + "</div>";
				
				//!!!!!!!!!!!!!!!!!!!
				//need to create the div tag that will work with the css and add that to the paginated link structure.
				//we need to add it to the paginatedlinkStructure AFTERRRR we complete the concatination
				//!!!!!!!!!!!!!!!
			}
			else //we need to have our first page be our currentPage and then only print out 10 pages worth of pagination then have a (...)
			{
				//problem: doesnt print out all 10 pages. starts with current one as 1st and then prints to last page
				//
				//***************************************START EXPERIMENT
				//Integer midNum = totalPages - currentPage;
				if(currentPage > 4)
				{
					//then have our current page be our "half way" page through the 10 pagination set and start 4 pages less than our current page
					Integer startingPage = currentPage - 4;
					Integer endingPage = startingPage + 10;
					if(endingPage > totalPages)
					{
						endingPage = totalPages;
					}
					countingtocurrentPage = startingPage;
					while(countingtocurrentPage <= endingPage)
					{
						if(countingtocurrentPage == currentPage)
						{
							paginatedlinkStructure = paginatedlinkStructure + " " + Integer.toString(currentPage);
						}
						else
						{
							paginatedlinkStructure = paginatedlinkStructure + " " +  linkEntry + categoryidString + "currentPage=" + countingtocurrentPage + "'>" + countingtocurrentPage + linkClosing;
							
						}
						countingtocurrentPage ++;
					}
					
				}
				else
				{
					countingtocurrentPage = 1;
					//while(countingtocurrentPage <= startingPage + 10 && countingtocurrentPage != totalPages)
					
					while(countingtocurrentPage <= totalPages)
					{
						
							if(countingtocurrentPage == currentPage)
							{
								paginatedlinkStructure = paginatedlinkStructure + " " + Integer.toString(currentPage);
							}
							else
							{
								paginatedlinkStructure = paginatedlinkStructure + " " +  linkEntry + categoryidString + "currentPage=" + countingtocurrentPage + "'>" + countingtocurrentPage + linkClosing;
								
							}
							countingtocurrentPage ++;
					}
					
					
				}
				Integer endingPage = countingtocurrentPage;
			
				
				//create a previous link page
				if(currentPage != 1 && totalPages != 1) //then create a previous page link
				{
					Integer previouspageNumber = currentPage - 1;
					previousPage = linkEntry + categoryidString + "currentPage=" + previouspageNumber + "'>" + " Previous Page " + linkClosing;
				}
				
				//create a  next page if the current page is not the last page
				if(currentPage + 1 != countingtocurrentPage)
				{
					countingtocurrentPage = currentPage + 1;
					nextPage = linkEntry + categoryidString + "currentPage=" + countingtocurrentPage + "'>" + " Next Page " + linkClosing;
					if(endingPage - 1 != totalPages)
					{
						nextPage = " ... " + nextPage;
					}
				}
				
				paginatedlinkStructure = "<div class='pagination'>" +  previousPage + paginatedlinkStructure + nextPage + "</div>";
				
				//!!!!!!!!!!!!!!!!!!!
				//need to create the div tag that will work with the css and add that to the paginated link structure.
				//we need to add it to the paginatedlinkStructure AFTERRRR we complete the concatination
				//!!!!!!!!!!!!!!!
			}
		}
		return paginatedlinkStructure;
	}
	public Integer getCategoryId() 
	{
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) 
	{
		this.categoryId = categoryId;
	}
	public Integer getCurrentPage() 
	{
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) 
	{
		this.currentPage = currentPage;
	}
	public Integer getProductsperPage() 
	{
		return productsperPage;
	}
	public void setProductsperPage(Integer productsperPage) 
	{
		this.productsperPage = productsperPage;
	}
	public Integer getTotalProducts() 
	{
		return totalProducts;
	}
	public void setTotalProducts(Integer totalProducts) 
	{
		this.totalProducts = totalProducts;
	}
	public String getPaginatedlinkStructure() 
	{
		return paginatedlinkStructure;
	}
	public void setPaginatedlinkStructure(String paginatedlinkStructure) 
	{
		this.paginatedlinkStructure = paginatedlinkStructure;
	}
	
	//need to build pagination link structure. should not show every single page 
	//possible, because there might be hundreds of pages. should show only 10 or other
	//set amount. Then have a (...) to represent that there are more pages. 
	
	//when they move forward in the page structure shown, the paginatedlinkstructure should
	//move forward in tandem. 
	//for example, if all that is shown is (1, 2, 3, 4, 5 ... next page), when they click on 
	//the link for page 3 they should then see a paginatedlink structure as such:
	//(previous page 3, 4, 5, 6, 7 ... next page).
	//If they run out of pages, and move forward to page 3, as such: (1, 2, 3, 4, 5 ... next page),
	//then they should see a paginatedlinkstructure as such (previous page  3, 4, 5).
	
	//If they are at the beginning of the paginatedlinkstructure, the previous page link should be
	//disabled.
	
	
	
}
