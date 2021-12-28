package com.freeads.freeads.controller;

import com.freeads.freeads.model.User;
import com.freeads.freeads.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Transactional
public class AdminsController 
{
	@Autowired
	private IUserService userService;

	@GetMapping( "/AdminUsers" )
	public String FindAdmins( Model model )
	{
		var adminUsers = ( List<User> ) userService.FindAll();
		model.addAttribute( "users", adminUsers );
		
		return "ShowAdminUsersView";
	}

	@GetMapping( "/EditAdminUser" )
	public String EditAdmin( @RequestParam long id, Model model )
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
	public String EditAdminSubmission( @ModelAttribute User adminUser, Model model )
	{
		try
		{
			userService.UpdateAdminUserData( adminUser );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}

		return "redirect:AdminUsers";
	}

	@GetMapping( "/DeleteAdminUser" )
	public String DeleteAdmin( @RequestParam long id )
	{
		try
		{
			userService.DeleteAdminUser( id );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}

		return "redirect:AdminUsers";
	}
}
