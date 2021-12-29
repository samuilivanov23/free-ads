package com.freeads.freeads.controller;

import com.freeads.freeads.model.User;
import com.freeads.freeads.service.IUserService;
import com.freeads.freeads.service.ICartService;
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
public class AdminsController 
{
	@Autowired
	private IUserService userService;

	@Autowired
	private ICartService cartService;

	@GetMapping( "/AdminUsers" )
	public String FindAdminUsers( Model model )
	{
		var adminUsers = ( List<User> ) userService.FindAll();
		model.addAttribute( "users", adminUsers );
		
		return "ShowAdminUsersView";
	}

	@GetMapping( "/EditAdminUser" )
	public String EditAdminUser( @RequestParam long id, Model model )
	{
		try
		{
			User adminUser = ( User ) userService.FindAdminById( id );
			model.addAttribute( "user", adminUser );
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return "redirect:AdminUsers";
		}

		return "EditAdminUserView";
	}

	@PostMapping( "/EditAdminUser" )
	public String EditAdminUserSubmission( @ModelAttribute User adminUser )
	{
		try
		{
			userService.UpdateAdminUser( adminUser );
		}
		catch( Exception exception ) { exception.printStackTrace(); }

		return "redirect:AdminUsers";
	}

	@GetMapping( "/DeleteAdminUser" )
	public String DeleteAdminUser( @RequestParam long id )
	{
		try
		{
			userService.DeleteAdminUser( id );
		}
		catch( Exception exception ) { exception.printStackTrace(); }

		return "redirect:AdminUsers";
	}

	@GetMapping( "/InsertAdminUser" )
	public String InsertAdminUser()
	{
		return "InsertAdminUserView";
	}

	@PostMapping( "/InsertAdminUser" )
	public String InsertAdminUserSubmission( @ModelAttribute User adminUser )
	{
		try
		{
			adminUser.setCartId( cartService.InsertCart() );
			long adminUserId = userService.InsertAdminUser( adminUser );
		}
		catch( Exception exception ) 
		{ 
			exception.printStackTrace();
			return "redirect:AdminUsers";
	   	}

		return "redirect:AdminUsers";
	}
}
