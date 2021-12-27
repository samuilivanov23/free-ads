package com.freeads.freeads.service;

import com.freeads.freeads.model.User;
import java.util.List;

public interface IUserService
{
	List<User> FindAll();
	User FindById( long id );
	void UpdateUserData( User user );
}
