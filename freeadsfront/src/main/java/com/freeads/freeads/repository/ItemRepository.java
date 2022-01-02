package com.freeads.freeads.repository;

import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import com.freeads.freeads.DataBaseManager;
import java.util.List;
import java.util.ArrayList;
import com.freeads.freeads.model.Item;

@Repository
public class ItemRepository 
{
	public List<Item> findAllItems()
	{
		List<Item> items = null;
		Item tmpItem = null;
		Statement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			statement = dbConn.createStatement();
			result = statement.executeQuery( "SELECT * FROM items WHERE is_deleted=false ORDER BY id" );
			items = new ArrayList<Item>();

			while( result.next() )
			{
				tmpItem = new Item();
				tmpItem.setId( result.getLong( 1 ) );
				tmpItem.setInsertedAt( result.getString( 2 ) );
				tmpItem.setName( result.getString( 3 ) );
				tmpItem.setDescription( result.getString( 4 ) );
				tmpItem.setSalesmanUserId( result.getLong( 5 ) );
				tmpItem.setCategoryId( result.getLong( 6 ) );
				tmpItem.setCount( result.getInt( 7 ) );
				tmpItem.setPrice( result.getDouble( 8 ) );
				tmpItem.setImageName( result.getString( 9 ) );
				tmpItem.setIsDeleted( result.getBoolean( 10 ) );

				items.add( tmpItem );
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
	
		return items;
	}

	public boolean addToFavourites( long userId, long itemId )
	{
		boolean isItemAddedToFavourites = false;
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );

			statement = dbConn.prepareStatement( "INSERT INTO userfavourites_items (user_id, item_id) values(?, ?) RETURNING user_id" );
			statement.setLong( 1, userId );
			statement.setLong( 2, itemId );
			
			result = statement.executeQuery();	
			result.next();
			
			if( result.getLong( 1 ) == userId )
			{
				isItemAddedToFavourites = true;
				transactionStatement.executeUpdate( "COMMIT" );
			}
			else
			{
				transactionStatement.executeUpdate( "ROLLBACK" );
			}
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement, transactionStatement );
		}

		return isItemAddedToFavourites;
	}
}
