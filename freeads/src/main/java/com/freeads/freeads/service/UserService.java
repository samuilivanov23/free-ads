package com.freeads.freeads.service;

import com.freeads.freeads.model.User;
import com.freeads.freeads.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService 
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> FindAll() 
	{
		var users = ( List<User> ) userRepository.findAll();
		return users;
	}
	
	@Override
	public User FindAdminById( long id )
	{
		var user = ( User ) userRepository.findAdminById( id );
		return user;
	}

	@Override
	public void UpdateAdminUserData( User user )
	{
		userRepository.updateAdminUserData( user.getId(),
									   user.getFirstName(),
			   						   user.getLastName(),
									   user.getUsername(),
									   user.getEmailAddress() );
	}

	@Override
	public void DeleteAdminUser( long id )
	{
		userRepository.deleteAdminUser( id );
	}
}
