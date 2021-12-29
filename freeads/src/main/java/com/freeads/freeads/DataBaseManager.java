package com.freeads.freeads;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.util.Properties;

public class DataBaseManager
{
	public static Connection ConnectToDatabase2()
	{
		try
		{
			Properties props = new Properties();
			String dbSettingsPropertyFile = "/etc/freeadsapp/JDBCSettings.properties";
					
			FileReader fReader = new FileReader(dbSettingsPropertyFile);
			props.load(fReader);

			String dbDriverClass = props.getProperty("db.driver.class");
			String dbConnUrl = props.getProperty("db.conn.url");
			String dbUserName = props.getProperty("db.username");
			String dbPassword = props.getProperty("db.password");

			try
			{
				if(!"".equals(dbDriverClass) && !"".equals(dbConnUrl))
				{
					/* Register jdbc driver class. */
					Class.forName(dbDriverClass);

					Connection dbConn = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
					return dbConn;
				}
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
				return null;
			}
		}
		catch ( IOException exception )
		{
			System.out.println( "Db configuration file not found at /etc/freeadsapp/JDBCSettings.properties!" );
			exception.printStackTrace();
			return null;
		}

		return null;
	}

	public static void CloseConnection( Connection dbConn, ResultSet result, Statement statement )
	{
		try
		{
			if( result != null ) { result.close(); }
		}
		catch( Exception resultException )
		{
			resultException.printStackTrace();
		}

		try
		{
			if( statement != null ) { statement.close(); }
		}
		catch( Exception stException )
		{
			stException.printStackTrace();
		}

		try
		{
			if( dbConn != null ) { dbConn.close(); }
		}
		catch( Exception dbConnException )
		{
			dbConnException.printStackTrace();
		}
	}
}
