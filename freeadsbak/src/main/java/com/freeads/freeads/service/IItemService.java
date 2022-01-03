package com.freeads.freeads.service;

import com.freeads.freeads.model.Item;
import java.util.List;

public interface IItemService
{
	List<Item> FindAllActiveItems( );
	List<Item> FindAllActiveItemsFiltered( String startDate, String endDate );
}
