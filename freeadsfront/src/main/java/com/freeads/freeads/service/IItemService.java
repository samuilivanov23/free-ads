package com.freeads.freeads.service;

import com.freeads.freeads.model.Item;
import java.util.List;

public interface IItemService
{
	List<Item> FindAllItems();
	boolean AddToFavourites( long userId, long itemId );
}
