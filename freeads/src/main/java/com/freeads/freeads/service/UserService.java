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
	private UserRepository repository;

	@Override
	public List<User> findAll() 
	{
		var users = ( List<User> ) repository.findAll();
		return users;
	}
}
