package com.freeads.freeads.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "users" )
public class User
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )

	private long id;
	private String insertedAt;
	private String firstName;
	private String lastName;
	private String username;
	private String emailAddress;
	private String password;
	private boolean authenticated;
	private long roleId;

	public User() {};

	public User( long id, 
				 String insertedAt, 
				 String firstName, 
				 String lastName, 
				 String username,
				 String emailAddress, 
				 String password )
	{
		this.id = id;
		this.insertedAt = insertedAt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.emailAddress = emailAddress;
		this.authenticated = false;
		this.roleId = -1;
	}

	public long getId() { return this.id; }
	public void setId( long id ) { this.id = id; }

	public String getInsertedAt() { return this.insertedAt; }
	public void setInsertedAt( String insertedAt ) { this.insertedAt = insertedAt; }
	
	public String getFirstName() { return this.firstName; }
	public void setFirstName( String firstName ) { this.firstName = firstName; }
	
	public String getLastName() { return this.lastName; }
	public void setLastName( String lastName ) { this.lastName = lastName; }
	
	public String getUsername() { return this.username; }
	public void setUsername( String username ) { this.username = username; }

	public String getEmailAddress() { return this.emailAddress; }
	public void setEmailAddress( String emailAddress ) { this.emailAddress = emailAddress; }

	public String getPassword() { return this.password; }
	public void setPassword( String password ) { this.password = password; }

	public boolean getAuthenticated() { return this.authenticated; }
	public void setAuthenticated( boolean authenticated ) { this.authenticated = authenticated; }
	
	public long getRoleId() { return this.roleId; }
	public void setRoleId( long roleId ) { this.roleId = roleId; }

	@Override
	public int hashCode() 
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode( this.id );
        hash = 79 * hash + Objects.hashCode( this.firstName );
		hash = 79 * hash + Objects.hashCode( this.lastName );
        hash = 79 * hash + Objects.hashCode( this.username );
		hash = 79 * hash + Objects.hashCode( this.emailAddress );
        return hash;
	}

	@Override
	public boolean equals( Object obj )
	{
		if( this == obj ) 
		{ 
			return true; 
		}
		else if( obj == null || obj.getClass() != this.getClass() ) 
		{ 
			return false; 
		}

		final User otherUser = ( User ) obj;

		if( !this.username.equals( otherUser.getUsername() ) )
		{
			return false;
		}

		if( !Objects.equals( this.username, otherUser.getUsername() ) )
		{
			return false;
		}

		return Objects.equals( this.id, otherUser.id );
	}

	@Override
	public String toString()
	{
		return "User[ " + "id:" + this.getId() + 
						  ", insertedAt:" + this.getInsertedAt() +
						  ", firstName:" + this.getFirstName() +
						  ", lastName:" + this.getLastName() +
						  ", authenticated" + this.getAuthenticated() + 
						  ", roleId:" + this.getRoleId() +
						  " ]"; 
	}
}
