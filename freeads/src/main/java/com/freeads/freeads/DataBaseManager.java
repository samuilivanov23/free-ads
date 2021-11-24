package com.freeads.freeads;

import java.io.FileReader;
import java.io.IOException;
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
					DatabaseMetaData dbMetaData = dbConn.getMetaData();
					String dbName = dbMetaData.getDatabaseProductName();
					String dbVersion = dbMetaData.getDatabaseProductVersion();

					System.out.println("Database Name : " + dbName);
					System.out.println("Database Version : " + dbVersion);
				}
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
		catch ( IOException exception )
		{
			System.out.println( "Db configuration file not found at /etc/freeadsapp/JDBCSettings.properties!" );
			exception.printStackTrace();
		}
	}
}
