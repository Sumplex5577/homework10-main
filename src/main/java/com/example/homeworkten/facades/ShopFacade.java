package com.example.homeworkten.facades;

import com.example.homeworkten.dtos.ShopDto;
import com.example.homeworkten.exceptions.NotFoundException;

import java.util.List;

public interface ShopFacade {
    ShopDto createShop(ShopDto shopDto);

    void deleteShop(ShopDto shopDto) throws NotFoundException;

    ShopDto getShopById(ShopDto shopDto) throws NotFoundException;

    List<ShopDto> getAllShops();
}