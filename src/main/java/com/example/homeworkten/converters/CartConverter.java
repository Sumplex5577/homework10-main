package com.example.homeworkten.converters;

import com.example.homeworkten.dtos.CartDto;
import com.example.homeworkten.models.Cart;

public class CartConverter {

    public static CartDto convertCartToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setIdCart(cart.getIdCart());
        cartDto.setPerson(cart.getPerson());
        cartDto.setProducts(cart.getProducts());
        cartDto.setSum(cart.getSum());
        return cartDto;
    }
}
