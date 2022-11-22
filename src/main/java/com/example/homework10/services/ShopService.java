package com.example.homework10.services;

import com.example.homework10.exceptions.NotFoundException;
import com.example.homework10.models.Shop;

import java.util.List;

public interface ShopService {
    Shop createShop(Shop shop);

    void deleteShop(Long idShop) throws NotFoundException;

    Shop getShopById(Long idShop) throws NotFoundException;

    List<Shop> getAllShops();
}
