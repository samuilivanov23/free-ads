package com.freeads.freeads.repository;

import com.freeads.freeads.model.Category;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import com.freeads.freeads.DataBaseManager;
import java.util.List;
import java.util.ArrayList;

@Repository
public class CategoryRepository
{
	public List<Category> findAllCategories()
	{
		List<Category> categories = null;
		Category tmpCategory = null;
		Statement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			statement = dbConn.createStatement();
			result = statement.executeQuery( "SELECT * FROM categories ORDER BY id" );
			categories = new ArrayList<Category>();

			while( result.next() )
			{
				tmpCategory = new Category();
				tmpCategory.setId( result.getLong( 1 ) );
				tmpCategory.setInsertedAt( result.getString( 2 ) );
				tmpCategory.setName( result.getString( 3 ) );
				categories.add( tmpCategory );
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
	
		return categories;
	}

	public Category findCategoryById( long id )
	{
		Category category = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.prepareStatement( "SELECT * FROM categories as c WHERE c.id=?" );	
			statement.setLong( 1, id );
			
			result = statement.executeQuery();
			result.next();

			category = new Category();
			category.setId( result.getLong( 1 ) );
			category.setInsertedAt( result.getString( 2 ) );
			category.setName( result.getString( 3 ) );	
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement );
		}
	
		return category;
	}

	public void updateCategory( Category category)
	{
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.prepareStatement( "UPDATE categories SET name=? where id=?" );	
			statement.setString( 1, category.getName() );
			statement.setLong( 2, category.getId() );
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

	public void deleteCategory( long id )
	{
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.prepareStatement( "DELETE FROM categories WHERE id=?" );
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

	public long insertCategory( Category category )
	{
		long categoryId = -1;
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{	
			statement = dbConn.prepareStatement( "INSERT INTO categories (name) values(?) RETURNING id" );
			statement.setString( 1, category.getName() );
			
			result = statement.executeQuery();
			result.next();
			
			categoryId = result.getLong( 1 );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement );
		}

		return categoryId;
	}
}
