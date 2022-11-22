package com.example.homework10.dtos;

import com.example.homework10.models.Shop;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private Long idProduct;

    private String name;

    private BigDecimal price;

    private Shop shop;

    private Long IdShop;
}
