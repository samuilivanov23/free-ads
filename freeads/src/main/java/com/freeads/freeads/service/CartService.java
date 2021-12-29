package com.freeads.freeads.service;

import com.freeads.freeads.model.Cart;
import com.freeads.freeads.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService
{
	@Autowired
	private CartRepository cartRepository;

	@Override 
	public long InsertCart()
	{
		return cartRepository.insertCart();
	}
}
