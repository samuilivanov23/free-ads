package com.freeads.freeads.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "items" )
public class Item
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )

	private long id;
	private String insertedAt;
	private String name;
	private String description;
	private long salesmanUserId;
	private long categoryId;
	private String categoryName; //not in the database but used in ActiveItemsAnalysisView
	private int count;
	private double price;
	private String imageName;
	private boolean isDeleted;
	private boolean isDeactivated;

	public Item() {};

	public Item( long id, 
				 String insertedAt, 
				 String name, 
				 String description, 
				 long salesmanUserId,
				 long categoryId,
				 String categoryName, 
				 int count,
				 double price,
				 String imageName,
				 boolean isDeleted,
		   		 boolean isDeactivated )
	{
		this.id = id;
		this.insertedAt = insertedAt;
		this.name = name;
		this.description = description;
		this.salesmanUserId = salesmanUserId;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.count = count;
		this.price = price;
		this.imageName = imageName;
		this.isDeleted = isDeleted;
		this.isDeactivated = isDeactivated;
	}

	public long getId() { return this.id; }
	public void setId( long id ) { this.id = id; }

	public String getInsertedAt() { return this.insertedAt; }
	public void setInsertedAt( String insertedAt ) { this.insertedAt = insertedAt; }
	
	public String getName() { return this.name; }
	public void setName( String name ) { this.name = name; }
	
	public String getDescription() { return this.description; }
	public void setDescription( String description ) { this.description = description; }
	
	public long getSalesmanUserId() { return this.salesmanUserId; }
	public void setSalesmanUserId( long salesmanUserId ) { this.salesmanUserId = salesmanUserId; }

	public long getCategoryId() { return this.categoryId; }
	public void setCategoryId( long categoryId ) { this.categoryId = categoryId; }
	
	public String getCategoryName() { return this.categoryName; }
	public void setCategoryName( String categoryName ) { this.categoryName = categoryName; }

	public int getCount() { return this.count; }
	public void setCount( int count ) { this.count = count; }

	public double getPrice() { return this.price; }
	public void setPrice( double price ) { this.price = price; }
	
	public String getImageName() { return this.imageName; }
	public void setImageName ( String imageName ) { this.imageName = imageName; }

	public boolean getIsDeleted() { return this.isDeleted; }
	public void setIsDeleted( boolean isDeleted ) { this.isDeleted = isDeleted; }

	public boolean getIsDeactivated() { return this.isDeleted; }
	public void setIsDeactivated( boolean isDeleted ) { this.isDeleted = isDeleted; }

	@Override
	public int hashCode() 
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode( this.id );
        hash = 79 * hash + Objects.hashCode( this.name );
		hash = 79 * hash + Objects.hashCode( this.description );
        hash = 79 * hash + Objects.hashCode( this.salesmanUserId );
		hash = 79 * hash + Objects.hashCode( this.categoryId );
		hash = 79 * hash + Objects.hashCode( this.count );
		hash = 79 * hash + Objects.hashCode( this.price );
		hash = 79 * hash + Objects.hashCode( this.imageName );
		hash = 79 * hash + Objects.hashCode( this.isDeleted );
		hash = 79 * hash + Objects.hashCode( this.isDeactivated );

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

		final Item otherItem = ( Item ) obj;

		if( !(this.id == otherItem.getId()) )
		{
			return false;
		}

		return Objects.equals( this.id, otherItem.getId() );
	}

	@Override
	public String toString()
	{
		return "Item[ " + "id:" + this.getId() + 
						  ", insertedAt:" + this.getInsertedAt() +
						  ", name:" + this.getName() +
						  ", description:" + this.getDescription() +
						  ", salesmanUserId:" + this.getSalesmanUserId() + 
						  ", categoryId:" + this.getCategoryId() +
						  ", count:" + this.getCount() +
						  ", price:" + this.getPrice() +
						  ", imageName:" + this.getImageName() +
						  ", isDeleted:" + this.getIsDeleted() +
						  ", isDeactivated: " + this.getIsDeactivated() +
						  " ]"; 
	}
}
