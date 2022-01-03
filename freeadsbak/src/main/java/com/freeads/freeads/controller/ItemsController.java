package com.freeads.freeads.controller;

import com.freeads.freeads.model.User;
import com.freeads.freeads.model.Item;
import com.freeads.freeads.model.Category;
import com.freeads.freeads.service.IUserService;
import com.freeads.freeads.service.IItemService;
import com.freeads.freeads.service.ICategoryService;
import com.freeads.freeads.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;
import java.io.IOException;

@Controller
public class ItemsController 
{
	@Autowired 
	private IItemService itemService;
	
	@Autowired
	private ICategoryService categoryService;

	public static User loggedInUser = null;
	
	@GetMapping( "/ActiveItemAnalysis" )
	public String ItemAnalysis( Model model )
	{
		try
		{
			List<Item> activeItems = itemService.FindAllActiveItems( );
			model.addAttribute( "items", activeItems );
			model.addAttribute( "loggedInUser", HomeController.loggedInUser );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			return "redirect:";
		}
		
		return "ActiveItemsAnalysisView";
	}

	@GetMapping( "ActiveItemsFilter" )
	public String ActiveItemsFilter( @RequestParam( name = "startDate" ) String startDate, @RequestParam( name = "endDate" ) String endDate, Model model )
	{
		try
		{
			List<Item> activeItemsFiltered = itemService.FindAllActiveItemsFiltered( startDate, endDate );
			model.addAttribute( "items", activeItemsFiltered );
			model.addAttribute( "loggedInUser", HomeController.loggedInUser );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			return "redirect:";
		}

		return "ActiveItemsAnalysisView";
	}
}
