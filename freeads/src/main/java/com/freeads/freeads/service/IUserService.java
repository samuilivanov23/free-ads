package com.freeads.freeads.service;

import com.freeads.freeads.model.User;
import java.util.List;

public interface IUserService
{
	List<User> FindAll();
	User FindAdminById( long id );
	long InsertAdminUser( User adminUser );
	void UpdateAdminUser( User adminUser );
	void DeleteAdminUser( long id );
}
