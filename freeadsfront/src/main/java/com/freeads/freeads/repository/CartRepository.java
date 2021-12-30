package com.freeads.freeads.repository;

import com.freeads.freeads.model.Cart;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import com.freeads.freeads.DataBaseManager;

@Repository
public class CartRepository 
{
	public long insertCart()
	{
		int cartId = -1;
		Statement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.createStatement();
			result = statement.executeQuery( "INSERT INTO carts DEFAULT VALUES RETURNING id" );
			result.next();
			cartId = result.getInt( 1 );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, statement );
		}

		return cartId;
	}
}
