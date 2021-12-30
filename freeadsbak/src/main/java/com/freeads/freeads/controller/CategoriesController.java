package com.freeads.freeads.controller;

import com.freeads.freeads.model.Category;
import com.freeads.freeads.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Controller
@Transactional
public class CategoriesController 
{
	@Autowired
	private ICategoryService categoryService;

	@GetMapping( "/Categories" )
	public String FindCategories( Model model )
	{
		var categories = ( List<Category> ) categoryService.FindAll();
		model.addAttribute( "categories", categories );
		
		return "ShowCategoriesView";
	}

	@GetMapping( "/EditCategory" )
	public String EditCategory( @RequestParam long id, Model model )
	{
		try
		{
			Category category = categoryService.FindCategoryById( id );
			model.addAttribute( "category", category );
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return "redirect:Categories";
		}

		return "EditCategoryView";
	}

	@PostMapping( "/EditCategory" )
	public String EditCategorySubmission( @ModelAttribute Category category )
	{
		try
		{
			categoryService.UpdateCategory( category );
		}
		catch( Exception exception ) { exception.printStackTrace(); }

		return "redirect:Categories";
	}

	@GetMapping( "/DeleteCategory" )
	public String DeleteCategory( @RequestParam long id )
	{
		try
		{
			categoryService.DeleteCategory( id );
		}
		catch( Exception exception ) { exception.printStackTrace(); }

		return "redirect:Categories";
	}

	@GetMapping( "/InsertCategory" )
	public String InsertCategory()
	{
		return "InsertCategoryView";
	}

	@PostMapping( "/InsertCategory" )
	public String InsertCategorySubmission( @ModelAttribute Category category )
	{
		try
		{
			long categoryId = categoryService.InsertCategory( category );
		}
		catch( Exception exception ) 
		{ 
			exception.printStackTrace();
			return "redirect:Categories";
	   	}

		return "redirect:Categories";
	}
}
