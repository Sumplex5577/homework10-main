package com.example.homework10.facades;

import com.example.homework10.dtos.CartDto;
import com.example.homework10.dtos.PersonDto;
import com.example.homework10.exceptions.NotFoundException;

import java.util.List;

public interface CartFacade {
    CartDto createCartByPersonId(PersonDto personDto) throws NotFoundException;

    CartDto addProductByProductIdAndCartId(CartDto cartDto) throws NotFoundException;

    CartDto removeProductByProductIdAndCartId(CartDto cartDto) throws NotFoundException;

    void removeAllProductsFromCartById(CartDto cartDto)throws NotFoundException;

    List<CartDto> getAllCarts();

    CartDto getCartById(CartDto cartDto) throws NotFoundException;

    void removeCartById(CartDto cartDto) throws NotFoundException;
}
