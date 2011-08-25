package org.webhop.ywdc.client;

import java.util.ArrayList;
import java.util.List;

import org.webhop.ywdc.beans.Category;

public class CategoryBuilder 
{
	public List<Category> categoryList;
	public List<Category> parentList;
	public String suckerfishNavBar;

	public CategoryBuilder()
	{
		suckerfishNavBar = "<ul id='vertnav'>";
		categoryList = new ArrayList<Category>();
		parentList = new ArrayList<Category>();
		
	}
	
	public String wrappingFunction()
	{
		setParentList();
		for(Category c: parentList)
		{
			suckerfishNavBar = suckerfishNavBar + suckerfishCategoryBuilder(c);
			
			//need to find this current categories subcategories. if none then print the <li> for this category with link and </li>
			
		}
		suckerfishNavBar = suckerfishNavBar + "</ul>";
		return suckerfishNavBar;
	}
	public String suckerfishCategoryBuilder(Category currentCategory)
	{
		String subcategoryString = "<ul>";
		String tmpreturnString =  "<li><a href='/Ecommerce/categoryproduct.action?categoryId=" + currentCategory.getId() + "'>" + currentCategory.getName() + "</a>";
		
		List<Category> tmpsubcatList = getSubCategories(currentCategory.getId());
		
		if(tmpsubcatList.isEmpty() == true)
		{
			tmpreturnString = tmpreturnString + "</li>";
			return tmpreturnString;
		}
		else
		{
			for(Category cat: tmpsubcatList)
			{
				subcategoryString = subcategoryString + suckerfishCategoryBuilder(cat);
			}
			subcategoryString = subcategoryString + "</ul>";
			tmpreturnString = tmpreturnString + subcategoryString + "</li>";
			return tmpreturnString;
		}
		
	}
	
	public List<Category> setParentList()
	{
	
		
		for(Category c: categoryList)
		{
			if(c.getParentid() == 0 || c.getParentid() == c.getId())
			{
				parentList.add(c);
			}
		}
		return parentList;
		
	}
	public List<Category> getSubCategories(Integer categoryId)
	{
		List<Category> subcategoryList = new ArrayList<Category>();
		
		for(Category c: categoryList)
		{
			if(c.getParentid() == categoryId && c.getParentid() != c.getId())
			{
				subcategoryList.add(c);
			}
		}
		return subcategoryList;
		
	}
	public void setCategories(List<Category> categoryList)
	{
		this.categoryList = categoryList;
	}
	public void setSuckerFishNavBar(String suckerfishNavBar)
	{
		this.suckerfishNavBar = suckerfishNavBar;
	}
	public String getSuckerFishNavBar()
	{
		return suckerfishNavBar;
	}
	
}
