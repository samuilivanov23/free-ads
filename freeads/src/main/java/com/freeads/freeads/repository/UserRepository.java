package com.freeads.freeads.repository;

import com.freeads.freeads.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import com.freeads.freeads.DataBaseManager;
import com.freeads.freeads.service.UserService;

@Repository
public class UserRepository 
{
	private static long ADMIN_ROLE_ID = 1;
	
	public List<User> findAllAdmins()
	{
		List<User> adminUsers = null;
		User tmpUser = null;
		Statement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			statement = dbConn.createStatement();
			result = statement.executeQuery( "SELECT * FROM users where role_id=1 ORDER BY id" ); //role_id=1 is for admin users
			adminUsers = new ArrayList<User>();

			while( result.next() )
			{
				tmpUser = new User();
				tmpUser.setId( result.getLong( 1 ) );
				tmpUser.setInsertedAt( result.getString( 2 ) );
				tmpUser.setFirstName( result.getString( 3 ) );
				tmpUser.setLastName( result.getString( 4 ) );
				tmpUser.setUsername( result.getString( 5 ) );
				tmpUser.setEmailAddress( result.getString( 6 ) );
				tmpUser.setPassword( result.getString( 7 ) );
				tmpUser.setAuthenticated( result.getBoolean( 8 ) );
				tmpUser.setCartId( result.getLong( 9 ) );
				tmpUser.setRoleId( result.getLong( 10 ) );

				adminUsers.add( tmpUser );
			}
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement );
		}
	
		return adminUsers;
	}

	public User findAdminById( long id )
	{
		User adminUser = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.prepareStatement( "SELECT * FROM users as u WHERE u.id=?" );	
			statement.setLong( 1, id );
			
			result = statement.executeQuery();
			result.next();

			adminUser = new User();
			adminUser.setId( result.getLong( 1 ) );
			adminUser.setInsertedAt( result.getString( 2 ) );
			adminUser.setFirstName( result.getString( 3 ) );
			adminUser.setLastName( result.getString( 4 ) );
			adminUser.setUsername( result.getString( 5 ) );
			adminUser.setEmailAddress( result.getString( 6 ) );
			adminUser.setPassword( result.getString( 7 ) );
			adminUser.setAuthenticated( result.getBoolean( 8 ) );
			adminUser.setCartId( result.getLong( 9 ) );
			adminUser.setRoleId( result.getLong( 10 ) );

		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement );
		}
	
		return adminUser;
	}

	public void updateAdminUser( User adminUser )
	{
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.prepareStatement( "UPDATE users SET first_name=?, last_name=?, username=?, email_address=? where id=?" );	
			statement.setString( 1, adminUser.getFirstName() );
			statement.setString( 2, adminUser.getLastName() );
			statement.setString( 3, adminUser.getUsername() );
			statement.setString( 4, adminUser.getEmailAddress() );
			statement.setLong( 5, adminUser.getId() );
			statement.executeUpdate();
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement );
		}
	}

	public void deleteAdminUser( long id )
	{
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.prepareStatement( "DELETE FROM users WHERE id=?" );
			statement.setLong( 1, id );
			statement.executeUpdate(); //executeUpdate() because no data is returned by the query
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement );
		}
	}

	public long insertAdminUser( User adminUser )
	{
		long adminUserId = -1;
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			statement = dbConn.prepareStatement( "INSERT INTO users(first_name, last_name, username, email_address, password, cart_id, role_id) values(?, ?, ?, ?, ?, ?, ?) RETURNING id" );
			statement.setString( 1, adminUser.getFirstName() );
			statement.setString( 2, adminUser.getLastName() );
			statement.setString( 3, adminUser.getUsername() );
			statement.setString( 4, adminUser.getEmailAddress() );
			statement.setString( 5, UserService.GetEncryptedPassword( adminUser.getPassword() ) );
			statement.setLong( 6, adminUser.getCartId() );
			statement.setLong( 7, ADMIN_ROLE_ID );
			
			result = statement.executeQuery();	
			result.next();
			
			adminUserId = result.getLong( 1 );
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
