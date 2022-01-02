package com.freeads.freeads.service;

import com.freeads.freeads.model.Item;
import java.util.List;

public interface IItemService
{
	List<Item> FindAllItems();
	List<Item> FindAllFavouriteItems( long userId );
	boolean AddToFavourites( long userId, long itemId );
	boolean RemoveFromFavourites( long userId, long itemId );
	public void InsertItem( Item item );
	public boolean DeleteItem( long itemId );
	public boolean EditItem( Item item );
	public boolean DeactivateItem( long itemId );
}
