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

	public boolean AddToFavourites( long userId, long itemId )
	{
		return itemRepository.addToFavourites( userId, itemId );
	}
}
