package com.example.homeworkten.converters;

import com.example.homeworkten.dtos.ShopDto;
import com.example.homeworkten.models.Shop;

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