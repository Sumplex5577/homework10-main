package com.example.homework10.dtos;

import com.example.homework10.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopDto {
    private Long idShop;
    private String name;
    private String link;
    private List<Product> products;

}



