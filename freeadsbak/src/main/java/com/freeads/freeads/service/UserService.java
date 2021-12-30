package com.freeads.freeads.service;

import com.freeads.freeads.model.User;
import com.freeads.freeads.repository.UserRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

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
		return userRepository.insertAdminUser( adminUser );
	}

	//User Operations methods
	public static String GetEncryptedPassword( String password )
	{
		return DigestUtils.sha256Hex( password );
	}
}
