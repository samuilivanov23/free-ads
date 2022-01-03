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

@Repository
public class ItemRepository 
{
	public List<Item> findAllActiveItems( String... startEndDates )
	{
		List<Item> items = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			String sql = "";
			if( startEndDates.length > 0 )
			{
				sql = "SELECT i.*, c.name FROM items AS i JOIN categories AS c ON i.category_id=c.id where i.is_deleted=false AND i.is_deactivated=true and i.inserted_at>=?::TIMESTAMP and i.inserted_at<=?::TIMESTAMP ";
				
				statement = dbConn.prepareStatement( sql );
				statement.setString( 1, startEndDates[0] ); //first string param is startDate
				statement.setString( 2, startEndDates[1] ); //second string param is endDate
			}
			else
			{
				sql = "SELECT i.*, c.name FROM items AS i JOIN categories AS c ON i.category_id=c.id where i.is_deleted=false AND i.is_deactivated=true" ;
				
				statement = dbConn.prepareStatement( sql );
			}
			
			result = statement.executeQuery();	
			
			Item tmpItem = null;
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
				tmpItem.setIsDeactivated( result.getBoolean( 11 ) );
				tmpItem.setCategoryName( result.getString( 12 ) ); //needed only for this interface. Refactoring needed

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
}
