package com.freeads.freeads.controller;

import com.freeads.freeads.model.User;
import com.freeads.freeads.service.IUserService;
import com.freeads.freeads.service.IEmailService;
import com.freeads.freeads.controller.HomeController;
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
public class UsersController 
{
	@Autowired
	private IUserService userService;

	@Autowired
	private IEmailService emailService;

	@GetMapping( "/RegisterUser" )
	public String RegisterUser()
	{
		return "RegisterUserView";
	}

	@PostMapping( "/RegisterUser" )
	@ResponseBody
	public boolean RegisterUserSubmission( @ModelAttribute User user )
	{
		boolean isEmailSentSuccessfully = false;
		try
		{
			isEmailSentSuccessfully = userService.RegisterUser( user );
		}
		catch( Exception exception ) 
		{ 
			exception.printStackTrace();
	   	}

		return isEmailSentSuccessfully;
	}

	@GetMapping( "/SignInUser" )
	public String SignInUser()
	{
		return "SignInUserView";
	}

	@PostMapping( "/SignInUser" )
	public String SignInUserSubmission( @ModelAttribute User user )
	{
		try
		{
			HomeController.loggedInUser = userService.SignInUser( user );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		return "redirect:";
	}

	@GetMapping( "/VerifyEmailAddress" )
	@ResponseBody
	public boolean VerifyEmailAddress( @RequestParam String token )
	{
		boolean isVerificationSuccessfull = false;
		try
		{
			isVerificationSuccessfull = emailService.VerifyEmailAddress( token );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}

		//TODO return basic template for successfull verification
		return isVerificationSuccessfull;
	}

	@GetMapping( "/LogoutUser" )
	public String LogoutUser()
	{
		HomeController.loggedInUser = null;
		return "redirect:";
	}
}
