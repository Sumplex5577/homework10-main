package com.example.homework10.facades;

import com.example.homework10.dtos.ShopDto;
import com.example.homework10.exceptions.NotFoundException;

import java.util.List;

public interface ShopFacade {
    ShopDto createShop(ShopDto shopDto);

    void deleteShop(ShopDto shopDto) throws NotFoundException;

    ShopDto getShopById(ShopDto shopDto) throws NotFoundException;

    List<ShopDto> getAllShops();
}