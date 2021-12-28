package com.freeads.freeads.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "carts" )
public class Cart
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )

	private long id;
	private String insertedAt;
	
	public Cart() {};

	public Cart( long id, String insertedAt )
	{
		this.id = id;
		this.insertedAt = insertedAt;
	}

	public long getId() { return this.id; }
	public void setId( long id ) { this.id = id; }

	public String getInsertedAt() { return this.insertedAt; }
	public void setInsertedAt( String insertedAt ) { this.insertedAt = insertedAt; }

	@Override
	public int hashCode() 
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode( this.id );
		hash = 79 * hash + Objects.hashCode( this.insertedAt );
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

		final Cart otherCart = ( Cart ) obj;

		if( !( this.id == otherCart.id ) )
		{
			return false;
		}

		if( !Objects.equals( this.id, otherCart.id) )
		{
			return false;
		}

		return Objects.equals(this.id, otherCart.id);
	}

	@Override
	public String toString()
	{
		return "Cart[ " + "id:" + this.getId() + ", insertedAt:" + this.getInsertedAt() + " ]";
	}
}
