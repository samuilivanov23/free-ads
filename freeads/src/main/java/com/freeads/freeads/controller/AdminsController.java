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

	@GetMapping( "/Administrators" )
	public String FindAdmins( Model model )
	{
		var users = ( List<User> ) userService.FindAll();
		model.addAttribute( "users", users );
		
		return "ShowAdminsView";
	}

	@GetMapping( "/EditAdmin" )
	public String EditAdmin( @RequestParam long id, Model model )
	{
		try
		{
			User user = ( User ) userService.FindById( id );
			System.out.println( user.getFirstName() );
			model.addAttribute( "user", user );
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			try
			{
				return "redirect:Administrators";
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

		return "EditAdminView";
	}

	@PostMapping( "/EditAdmin" )
	public String EditAdminSubmission( @ModelAttribute User user, Model model )
	{
		//TODO save to database
		System.out.println( user.getFirstName() + " " + user.getEmailAddress() );
		userService.UpdateUserData( user );
		
		return "redirect:Administrators";
	}
}
