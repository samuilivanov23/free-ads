package com.freeads.freeads.service;

import com.freeads.freeads.model.User;
import java.util.List;

public interface IUserService
{
	List<User> FindAll();
	User FindAdminById( long id );
	boolean RegisterUser( User user );
	User SignInUser( User user );
	void UpdateAdminUser( User adminUser );
	void DeleteAdminUser( long id );
}
