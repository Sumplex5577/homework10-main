package com.example.homeworkten.services;

import com.example.homeworkten.exceptions.NotFoundException;
import com.example.homeworkten.models.Shop;

import java.util.List;

public interface ShopService {
    Shop createShop(Shop shop);

    void deleteShop(Long idShop) throws NotFoundException;

    Shop getShopById(Long idShop) throws NotFoundException;

    List<Shop> getAllShops();
}
