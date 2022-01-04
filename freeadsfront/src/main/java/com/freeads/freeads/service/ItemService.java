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

	@Override
	public List<Item> FindAllItems()
	{
		return itemRepository.findAllItems();
	}
	
	@Override
	public Item FindById( long itemId )
	{
		return itemRepository.findById( itemId );
	}

	@Override
	public List<Item> FindAllFavouriteItems( long userId )
	{
		return itemRepository.findAllFavouriteItems( userId );
	}

	@Override
	public List<Item> FindAllActiveItems()
	{
		return itemRepository.findAllActiveItems();
	}

	@Override
	public List<Item> FindAllActiveItemsFiltered( String startDate, String endDate )
	{
		return itemRepository.findAllActiveItems( startDate, endDate );
	}

	@Override
	public boolean AddToFavourites( long userId, long itemId )
	{
		return itemRepository.addToFavourites( userId, itemId );
	}

	@Override
	public boolean RemoveFromFavourites( long userId, long itemId )
	{
		return itemRepository.removeFromFavaurites( userId, itemId );
	}

	@Override
	public void InsertItem( Item item )
	{
		itemRepository.insertItem( item );
	}

	@Override
	public boolean DeleteItem( long itemId )
	{
		return itemRepository.deleteOrDeactivateItem( itemId, "is_deleted" );
	}

	@Override
	public void EditItem( Item item )
	{
		itemRepository.editItem( item );
	}

	@Override
	public boolean DeactivateItem( long itemId )
	{
		return itemRepository.deleteOrDeactivateItem( itemId, "is_deactivated" );
	}

	@Override
	public boolean AddItemToCart( String userFirstName, String userLastName, long userId, long itemId )
	{
		return itemRepository.addItemToCart( userFirstName, userLastName, userId, itemId );
	}
}
