package org.webhop.ywdc.admin;

import java.util.ArrayList;
import java.util.List;

import org.webhop.ywdc.beans.Category;

public class ChildCategoryFinder 
{
	public List<Category> categoryList;
	public Category parentCategory;
	public List<Category> finalcatList;

	public ChildCategoryFinder()
	{
		finalcatList = new ArrayList<Category>();
	}
	
	public List<Category> findAllRelatedCategories(Category currentCategory)
	{
		List<Category> tmpSubCategoryList = new ArrayList<Category>();
		for(Category c: categoryList)
		{
			Integer parentId = c.getParentid();
			if(parentId == currentCategory.getId())
			{
				tmpSubCategoryList.add(c);
				if(c.getId() != c.getParentid())
				{
					List<Category> recursiveAdditiveList = new ArrayList<Category>();
					recursiveAdditiveList = findAllRelatedCategories(c);
					tmpSubCategoryList.addAll(recursiveAdditiveList);
				}
				else
				{
					tmpSubCategoryList.add(c);
				}
			}
			
		}
		
		
		return tmpSubCategoryList;
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
	public List<Category> getCategoryList() 
	{
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) 
	{
		this.categoryList = categoryList;
	}
}
