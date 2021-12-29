package com.freeads.freeads.service;

import com.freeads.freeads.model.User;
import com.freeads.freeads.repository.UserRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import com.freeads.freeads.DataBaseManager;

@Service
public class UserService implements IUserService 
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> FindAll() 
	{
		return userRepository.findAllAdmins();
	}
	
	@Override
	public User FindAdminById( long id )
	{
		return userRepository.findAdminById( id );
	}

	@Override
	public void UpdateAdminUser( User adminUser )
	{
		userRepository.updateAdminUser( adminUser );
	}

	@Override
	public void DeleteAdminUser( long id )
	{
		userRepository.deleteAdminUser( id );	
	}

	@Override 
	public long InsertAdminUser( User adminUser )
	{
		return userRepository.inserrtAdminUser( adminUser );
	}
}
