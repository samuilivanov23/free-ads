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

	public List<Item> FindAllActiveItems()
	{
		return itemRepository.findAllActiveItems();
	}

	public List<Item> FindAllActiveItemsFiltered( String startDate, String endDate )
	{
		return itemRepository.findAllActiveItems( startDate, endDate );
	}
}
