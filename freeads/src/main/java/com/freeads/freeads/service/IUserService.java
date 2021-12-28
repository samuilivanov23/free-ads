package com.freeads.freeads.service;

import com.freeads.freeads.model.User;
import java.util.List;

public interface IUserService
{
	List<User> FindAll();
	User FindAdminById( long id );
	void UpdateAdminUserData( User user );
	void DeleteAdminUser( long id );
}
