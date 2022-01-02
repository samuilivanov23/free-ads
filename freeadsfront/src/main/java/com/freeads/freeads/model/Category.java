package com.freeads.freeads.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "categories" )
public class Category
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )

	private long id;
	private String insertedAt;
	private String name;
	
	public Category() {};

	public Category( long id, String insertedAt, String name )
	{
		this.id = id;
		this.insertedAt = insertedAt;
		this.name = name;
	}

	public long getId() { return this.id; }
	public void setId( long id ) { this.id = id; }

	public String getInsertedAt() { return this.insertedAt; }
	public void setInsertedAt( String insertedAt ) { this.insertedAt = insertedAt; }

	public String getName() { return this.name; }
	public void setName( String name ) { this.name = name; }

	@Override
	public int hashCode() 
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode( this.id );
		hash = 79 * hash + Objects.hashCode( this.insertedAt );
		hash = 79 * hash + Objects.hashCode( this.name );
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

		final Category otherCategory = ( Category ) obj;

		if( !( this.id == otherCategory.getId() ) )
		{
			return false;
		}

		if( !Objects.equals( this.id, otherCategory.id) )
		{
			return false;
		}

		return Objects.equals( this.id, otherCategory.id );
	}

	@Override
	public String toString()
	{
		return "Category[ " + "id:" + this.getId() + ", insertedAt:" + this.getInsertedAt() + ", name:" + this.getName() + " ]";
	}
}
