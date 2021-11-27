package com.freeads.freeads.controller;

import com.freeads.freeads.model.User;
import com.freeads.freeads.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminsController 
{
	@Autowired
	private IUserService userService;

	@GetMapping( "/Administrators" )
	public String FindUsers( Model model )
	{
		var users = ( List<User> ) userService.findAll();
		model.addAttribute( "users", users );
		
		return "ShowAdminsView";
	}
}
