package com.freeads.freeads.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.freeads.freeads.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.freeads.freeads.model.Item;

@Service
public class ItemService implements IItemService
{
	@Autowired ItemRepository itemRepository;

	public List<Item> FindAllItems()
	{
		return itemRepository.findAllItems();
	}

	public List<Item> FindAllFavouriteItems( long userId )
	{
		return itemRepository.findAllFavouriteItems( userId );
	}

	public boolean AddToFavourites( long userId, long itemId )
	{
		return itemRepository.addToFavourites( userId, itemId );
	}

	public boolean RemoveFromFavourites( long userId, long itemId )
	{
		return itemRepository.removeFromFavaurites( userId, itemId );
	}

	public void InsertItem( Item item )
	{
		itemRepository.insertItem( item );
	}

	public boolean DeleteItem( long itemId )
	{
		return itemRepository.deleteOrDeactivateItem( itemId, "is_deleted" );
	}

	public boolean EditItem( Item item )
	{
		return itemRepository.editItem( item );
	}

	public boolean DeactivateItem( long itemId )
	{
		return itemRepository.deleteOrDeactivateItem( itemId, "is_deactivated" );
	}
}
