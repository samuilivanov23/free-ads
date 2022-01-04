package com.freeads.freeads.service;

import com.freeads.freeads.model.Category;
import com.freeads.freeads.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override 
	public List<Category> FindAll()
	{
		return categoryRepository.findAllCategories();
	}

	@Override
	public Category FindCategoryById( long id )
	{
		return categoryRepository.findCategoryById( id );
	}

	@Override
	public void UpdateCategory( Category category )
	{
		categoryRepository.updateCategory( category );
	}

	@Override
	public void DeleteCategory( long id )
	{
		categoryRepository.deleteCategory( id );
	}

	@Override
	public long InsertCategory( Category category )
	{
		return categoryRepository.insertCategory( category );
	}
}
