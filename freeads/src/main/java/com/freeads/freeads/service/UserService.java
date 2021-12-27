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
	public User FindById( long id )
	{
		var user = ( User ) userRepository.findById( id );
		return user;
	}

	@Override
	public void UpdateUserData( User user )
	{
		System.out.println( user.getId() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmailAddress() );
		userRepository.updateUserData( user.getId(),
									   user.getFirstName(),
			   						   user.getLastName(),
									   user.getUsername(),
									   user.getEmailAddress() );
	}
}
