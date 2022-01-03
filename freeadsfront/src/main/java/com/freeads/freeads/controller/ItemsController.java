package com.freeads.freeads.controller;

import com.freeads.freeads.model.User;
import com.freeads.freeads.model.Item;
import com.freeads.freeads.model.Category;
import com.freeads.freeads.service.IUserService;
import com.freeads.freeads.service.IItemService;
import com.freeads.freeads.service.ICategoryService;
import com.freeads.freeads.service.IFileService;
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

	@Autowired
	private IFileService fileService;

	public static User loggedInUser = null;

	@GetMapping( "/Favourites" )
	public String FavoiritesList( Model model )
	{
		try
		{
			List<Item> favouriteItems = itemService.FindAllFavouriteItems( HomeController.loggedInUser.getId() );
			model.addAttribute( "items", favouriteItems );
			model.addAttribute( "loggedInUser", HomeController.loggedInUser );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			return "redirect:";
		}
		
		return "index";
	}

	@GetMapping( "/AddToFavourites" )
	@ResponseBody
	public boolean AddToFavourites( @RequestParam( name = "id" ) long itemId )
	{
		boolean isItemAddedToFavourites = false;

		try
		{
			isItemAddedToFavourites = itemService.AddToFavourites( HomeController.loggedInUser.getId(), itemId );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return isItemAddedToFavourites;	
	}

	@GetMapping( "/RemoveFromFavourites" )
	@ResponseBody
	public boolean RemoveFromFavourites( @RequestParam( name = "id" ) long itemId )
	{
		boolean isItemRemovedFromFavourites = false;

		try
		{
			isItemRemovedFromFavourites = itemService.RemoveFromFavourites( HomeController.loggedInUser.getId(), itemId );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return isItemRemovedFromFavourites;
	}
	
	@GetMapping( "/AddItem" )
	public String AddItem( Model model )
	{
		try
		{

			model.addAttribute( "loggedInUser", HomeController.loggedInUser );
			
			List<Category> categories = categoryService.FindAll();
			model.addAttribute( "categories", categories );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}

		return "InsertItemView";
	}

	@PostMapping( "/AddItem" )
	public String AddItemSubmission( @ModelAttribute Item item, @RequestParam("image") MultipartFile multipartFile )
	{
		try
		{
			item.setSalesmanUserId( HomeController.loggedInUser.getId() );
			item.setImageName( StringUtils.cleanPath( multipartFile.getOriginalFilename() ) );
	
			fileService.SaveFile( multipartFile );
			itemService.InsertItem( item );
        }
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		return "redirect:";
	}

	@GetMapping( "/DeleteItem" )
	@ResponseBody
	public boolean DeleteItem( @RequestParam( name = "id" ) long itemId )
	{
		boolean isItemDeleted = false;
		try
		{
			isItemDeleted = itemService.DeleteItem( itemId );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return isItemDeleted;
	}

	@GetMapping( "/DeactivateItem" )
	@ResponseBody
	public boolean DeactivateItem( @RequestParam( name = "id" ) long itemId )
	{
		boolean isItemDeactivated = false;
		try
		{
			isItemDeactivated = itemService.DeactivateItem( itemId );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return isItemDeactivated;
	}

	@GetMapping( "/EditItem" )
	public String EditItem( @RequestParam( name = "id" ) long itemId, Model model )
	{
		try
		{
			Item item = itemService.FindById( itemId );
			model.addAttribute( "item", item );

			List<Category> categories = categoryService.FindAll();
			model.addAttribute( "categories", categories );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return "EditItemView";
	}

	@PostMapping( "/EditItem" )
	public String EditItemSubmission( @ModelAttribute Item item, @RequestParam("image") MultipartFile multipartFile )
	{
		try
		{
			item.setSalesmanUserId( HomeController.loggedInUser.getId() );
			item.setImageName( StringUtils.cleanPath( multipartFile.getOriginalFilename() ) );
			
			fileService.SaveFile( multipartFile );
			itemService.EditItem( item );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return "redirect:";
	}

	@GetMapping( "/AddItemToCart" )
	@ResponseBody
	public boolean AddItemToCart( @RequestParam( name = "id" ) long itemId )
	{
		boolean isItemAddedToCart = false;
		try
		{
			isItemAddedToCart = itemService.AddItemToCart( HomeController.loggedInUser.getFirstName(), 
														   HomeController.loggedInUser.getLastName(),
														   HomeController.loggedInUser.getId(),
														   itemId );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}

		return isItemAddedToCart;
	}

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
