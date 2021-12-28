package com.freeads.freeads.service;

import com.freeads.freeads.model.User;
import com.freeads.freeads.repository.UserRepository;
import java.util.List;
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
	public void UpdateAdminUser( User adminUser )
	{
		userRepository.updateAdminUser( adminUser.getId(),
									    adminUser.getFirstName(),
			   						    adminUser.getLastName(),
									    adminUser.getUsername(),
									    adminUser.getEmailAddress() );
	}

	@Override
	public void DeleteAdminUser( long id )
	{
		userRepository.deleteAdminUser( id );
	}

	@Override 
	public long InsertAdminUser( User adminUser )
	{

		int adminUserId = -1;
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.prepareStatement( "INSERT INTO users(first_name, last_name, username, email_address, password, cart_id) values(?, ?, ?, ?, ?, ?) RETURNING id" );
			statement.setString( 1, adminUser.getFirstName() );
			statement.setString( 2, adminUser.getLastName() );
			statement.setString( 3, adminUser.getUsername() );
			statement.setString( 4, adminUser.getEmailAddress() );
			statement.setString( 5, adminUser.getPassword() );
			statement.setLong( 6, adminUser.getCartId() );
			result = statement.executeQuery();	

			result.next();
			adminUserId = result.getInt( 1 );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement );
		}

		return adminUserId;
	}
}
