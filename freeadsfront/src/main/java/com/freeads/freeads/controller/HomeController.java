package com.freeads.freeads.controller;

import com.freeads.freeads.model.User;
import com.freeads.freeads.model.Item;
import com.freeads.freeads.service.IUserService;
import com.freeads.freeads.service.IItemService;
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
public class HomeController 
{
	@Autowired 
	private IItemService itemService;
	
	public static User loggedInUser = null;

	@GetMapping( "/" )
	public String Index( Model model )
	{
		try
		{
			List<Item> items = itemService.FindAllItems();	
			model.addAttribute( "loggedInUser", loggedInUser );
			model.addAttribute( "items", items );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
			
		return "index";
	}
}
