package com.freeads.freeads.service;

import com.freeads.freeads.model.Category;
import java.util.List;

public interface ICategoryService
{
	List<Category> FindAll();
	Category FindCategoryById( long id );
	void UpdateCategory( Category category );
	void DeleteCategory( long id );
	long InsertCategory( Category category );
}
