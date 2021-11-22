package com.freeads.freeads;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.util.Properties;

public class DataBaseManager
{
	public static void ConnectToDatabase()
	{
		try
		{
			// Create Properties object.
			Properties props = new Properties();

			String dbSettingsPropertyFile = "/home/samuil/JDBCSettings.properties";
			// Properties will use a FileReader object as input.
			FileReader fReader = new FileReader(dbSettingsPropertyFile);

			// Load jdbc related properties in above file.
			props.load(fReader);

			// Get each property value.
			String dbDriverClass = props.getProperty("db.driver.class");

			String dbConnUrl = props.getProperty("db.conn.url");

			String dbUserName = props.getProperty("db.username");

			String dbPassword = props.getProperty("db.password");
			System.out.println( dbPassword );
			System.out.println( dbConnUrl );

			if(!"".equals(dbDriverClass) && !"".equals(dbConnUrl))
			{
				/* Register jdbc driver class. */
				Class.forName(dbDriverClass);

				// Get database connection object.
				Connection dbConn = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);

				// Get dtabase meta data.
				DatabaseMetaData dbMetaData = dbConn.getMetaData();

				// Get database name.
				String dbName = dbMetaData.getDatabaseProductName();

				// Get database version.
				String dbVersion = dbMetaData.getDatabaseProductVersion();

				System.out.println("Database Name : " + dbName);

				System.out.println("Database Version : " + dbVersion);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
