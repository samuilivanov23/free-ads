package com.freeads.freeads.repository;

import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import com.freeads.freeads.DataBaseManager;

@Repository
public class EmailRepository 
{
	public void assignVerificationTokenToUser( String token, long userId )
	{	
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );

			statement = dbConn.prepareStatement( "INSERT INTO verifications_email (user_id, token) values(?, ?)" );
			statement.setLong( 1, userId );
			statement.setString( 2, token );
			
			statement.executeUpdate();
			transactionStatement.executeUpdate( "COMMIT" );	
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			try
			{
				transactionStatement.executeUpdate( "ROLLBACK" );
			}
			catch( Exception exception2)
			{
				exception2.printStackTrace();
			}
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement, transactionStatement );
		}
	}

	public boolean verifyEmailAddress( String token )
	{
		boolean isVerificationSuccessfull = false;
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase2();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );

			statement = dbConn.prepareStatement( "UPDATE users SET authenticated=CASE (SELECT (inserted_at + '1 hour'::interval) > CURRENT_TIMESTAMP(6) FROM verifications_email WHERE token=?) WHEN true THEN true WHEN false THEN false END WHERE id=(SELECT user_id FROM verifications_email WHERE token=?) RETURNING (SELECT (inserted_at + '1 hour'::interval) > CURRENT_TIMESTAMP(6) FROM verifications_email WHERE token=?);" );
			statement.setString( 1, token );
			statement.setString( 2, token );
			statement.setString( 3, token );
			
			result = statement.executeQuery();
			result.next();
			isVerificationSuccessfull = result.getBoolean( 1 );

			transactionStatement.executeUpdate( "COMMIT" );	
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			try
			{
				transactionStatement.executeUpdate( "ROLLBACK" );
			}
			catch( Exception exception2)
			{
				exception2.printStackTrace();
			}
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement, transactionStatement );
		}

		return isVerificationSuccessfull;
	}
}	
