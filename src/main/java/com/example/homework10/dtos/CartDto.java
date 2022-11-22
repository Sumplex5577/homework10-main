package com.example.homework10.dtos;

import com.example.homework10.models.Person;
import com.example.homework10.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartDto {
    private Long IdCart;
    private Person person;
    private List<Product> products;
    private BigDecimal sum;
    private Long idProduct;
}
