package com.freeads.freeads;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FreeAdsApplication {

	public static void main( String[] args ) 
	{
		SpringApplication.run( FreeAdsApplication.class, args );
		DataBaseManager.ConnectToDatabase();
	}

	@Bean
	public CommandLineRunner commandLineRunner( ApplicationContext ctx ) {
		return args -> 
		{

			System.out.println( "Let's inspect the beans provided by Spring Boot:" );

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort( beanNames );
			for ( String beanName : beanNames ) {
				System.out.println( beanName );
			}

		};
	}

	/*public void ConnectToDatabase()
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

			if(!"".equals(dbDriverClass) && !"".equals(dbConnUrl))
			{
				/* Register jdbc driver class. */
			/*	Class.forName(dbDriverClass);

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
	}*/
}
