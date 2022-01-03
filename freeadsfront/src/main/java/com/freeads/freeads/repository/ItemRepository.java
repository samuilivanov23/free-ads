package com.freeads.freeads.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.freeads.freeads.DataBaseManager;
import java.util.List;
import java.util.ArrayList;
import com.freeads.freeads.model.Item;
import com.freeads.freeads.service.IEmailService;

@Repository
public class ItemRepository 
{

	@Autowired
	private IEmailService emailService;

	public List<Item> findAllItems()
	{
		List<Item> items = null;
		Statement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			statement = dbConn.createStatement();
			result = statement.executeQuery( "SELECT * FROM items WHERE is_deleted=false AND is_deactivated=false ORDER BY id" );
			items = mapResultToList( result );
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

	public List<Item> findAllFavouriteItems( long userId )
	{
		List<Item> items = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			statement = dbConn.prepareStatement( "SELECT * FROM items AS i JOIN userfavourites_items AS ufi ON i.id=ufi.item_id WHERE i.is_deleted=false AND i.is_deactivated=false AND ufi.user_id=?" );
			statement.setLong( 1, userId );
			
			result = statement.executeQuery();	
			items = mapResultToList( result );
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

	public boolean removeFromFavaurites( long userId, long itemId )
	{
		boolean isItemRemovedFromFavourites = false;
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );

			statement = dbConn.prepareStatement( "DELETE FROM userfavourites_items WHERE user_id=? AND item_id=? RETURNING user_id" );
			statement.setLong( 1, userId );
			statement.setLong( 2, itemId );
			
			result = statement.executeQuery();	
			result.next();
			
			if( result.getLong( 1 ) == userId )
			{
				isItemRemovedFromFavourites = true;
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

		return isItemRemovedFromFavourites;
	}

	public void insertItem( Item item )
	{
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );

			statement = dbConn.prepareStatement( "INSERT INTO items (name, description, salesman_user_id, category_id, count, price, image_name) VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id" );
			statement.setString( 1, item.getName() );
			statement.setString( 2, item.getDescription() );
			statement.setLong( 3, item.getSalesmanUserId() );
			statement.setLong( 4, item.getCategoryId() );
			statement.setInt( 5, item.getCount() );
			statement.setDouble( 6, item.getPrice() );
			statement.setString( 7, item.getImageName() );
			
			result = statement.executeQuery();
			result.next();
			
			if( result.getLong( 1 ) > 0 )
			{
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
	}

	public boolean deleteOrDeactivateItem( long itemId, String deleteOrDeactivateColumn )
	{
		boolean isItemDeleted = false;
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );
			
			String query = "UPDATE items SET " + deleteOrDeactivateColumn + "=true WHERE id=? RETURNING id" ; 
			statement = dbConn.prepareStatement( query );
			statement.setLong( 1, itemId );
			
			result = statement.executeQuery();	
			result.next();
			
			if( result.getLong( 1 ) == itemId )
			{
				isItemDeleted = true;
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

		return isItemDeleted;
	}

	public boolean editItem( Item item )
	{
		return false;
	}

	public boolean addItemToCart( String userFirstName, String userLastName, long userId, long itemId )
	{
		boolean isItemAddedToCart = false;
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );
			
			statement = dbConn.prepareStatement( "INSERT INTO carts_items (user_id, item_id) VALUES(?, ?) RETURNING user_id" );
			statement.setLong( 1, userId );
			statement.setLong( 2, itemId );
			
			result = statement.executeQuery();	
			result.next();
			
			if( result.getLong( 1 ) == userId )
			{
				isItemAddedToCart = true;
				transactionStatement.executeUpdate( "COMMIT" );
			}
			else
			{
				transactionStatement.executeUpdate( "ROLLBACK" );
			}

			try
			{
				emailService.SendProductStatusNotifMail( userFirstName, userLastName, itemId );
			}
			catch( Exception emailException )
			{
				emailException.printStackTrace();
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

		return isItemAddedToCart;
	}

	private List<Item> mapResultToList( ResultSet result ) throws SQLException
	{
		Item tmpItem = null;
		List<Item> items = new ArrayList<Item>();

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

		return items;
	}
}
