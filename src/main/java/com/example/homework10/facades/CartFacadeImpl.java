package com.example.homework10.facades;

import com.example.homework10.converters.CartConverter;
import com.example.homework10.dtos.CartDto;
import com.example.homework10.dtos.PersonDto;
import com.example.homework10.exceptions.NotFoundException;
import com.example.homework10.models.Cart;
import com.example.homework10.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.homework10.converters.CartConverter.convertCartToCartDto;

@Component
public class CartFacadeImpl implements CartFacade {

    @Autowired
    private CartService cartService;

    @Override
    public CartDto createCartByPersonId(PersonDto personDto) throws NotFoundException {
        Cart Cart = cartService.createCartByPersonId(personDto.getIdPerson());
        return convertCartToCartDto(Cart);
    }

    @Override
    public CartDto addProductByProductIdAndCartId(CartDto cartDto) throws NotFoundException {
        Cart cart = cartService.addProductByProductIdAndCartId(cartDto.getIdCart(), cartDto.getIdProduct());
        return convertCartToCartDto(cart);
    }

    @Override
    public CartDto removeProductByProductIdAndCartId(CartDto cartDto) throws NotFoundException {
        Cart removeFromCart = cartService.removeProductByProductIdAndCartId(cartDto.getIdCart(), cartDto.getIdProduct());
        return convertCartToCartDto(removeFromCart);
    }

    @Override
    public void removeAllProductsFromCartById(CartDto cartDto) throws NotFoundException {
        cartService.removeAllProductsFromCartById(cartDto.getIdCart());
    }

    @Override
    public List<CartDto> getAllCarts() {
        return cartService.getAllCarts().stream().map(CartConverter::convertCartToCartDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartDto getCartById(CartDto cartDto) throws NotFoundException {
        Cart cart = cartService.getCartById(cartDto.getIdCart());
        return convertCartToCartDto(cart);
    }

    @Override
    public void removeCartById(CartDto cartDto) throws NotFoundException {
        cartService.removeCartById(cartDto.getIdCart());
    }
}
