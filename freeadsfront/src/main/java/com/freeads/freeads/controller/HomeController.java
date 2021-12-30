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
public class HomeController 
{
	public static User loggedInUser = null;

	@GetMapping( "/" )
	public String FindAdminUsers( Model model )
	{
		model.addAttribute( "loggedInUser", loggedInUser );
		return "index";
	}
}
