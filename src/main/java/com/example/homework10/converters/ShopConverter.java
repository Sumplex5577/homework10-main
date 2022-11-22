package com.example.homework10.converters;

import com.example.homework10.dtos.ShopDto;
import com.example.homework10.models.Shop;

public class ShopConverter {
    public static ShopDto convertShopToShopDto(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.setIdShop(shop.getIdShop());
        shopDto.setName(shop.getName());
        shopDto.setLink(shop.getLink());
        shopDto.setProducts(shop.getProducts());
        return shopDto;
    }

    public static Shop convertShopDtoToShop(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setIdShop(shopDto.getIdShop());
        shop.setName(shopDto.getName());
        shop.setLink(shopDto.getLink());
        shop.setProducts(shopDto.getProducts());
        return shop;
    }
}