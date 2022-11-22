package com.example.homework10.facades;

import com.example.homework10.converters.ShopConverter;
import com.example.homework10.dtos.ShopDto;
import com.example.homework10.exceptions.NotFoundException;
import com.example.homework10.models.Shop;
import com.example.homework10.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.homework10.converters.ShopConverter.convertShopDtoToShop;
import static com.example.homework10.converters.ShopConverter.convertShopToShopDto;

@Component
public class ShopFacadeImpl implements ShopFacade {

    @Autowired
    private ShopService shopService;

    @Override
    public ShopDto createShop(ShopDto shopDto) {
        Shop Shop=shopService.createShop(convertShopDtoToShop(shopDto));
        return convertShopToShopDto(Shop);
    }

    @Override
    public void deleteShop(ShopDto shopDto) throws NotFoundException {
        shopService.deleteShop(shopDto.getIdShop());
    }

    @Override
    public ShopDto getShopById(ShopDto shopDto) throws NotFoundException {
        Shop getShop = shopService.getShopById(shopDto.getIdShop());
        return convertShopToShopDto(getShop);
    }

    @Override
    public List<ShopDto> getAllShops() {
        return shopService.getAllShops().stream().map(ShopConverter::convertShopToShopDto)
                .collect(Collectors.toList());
    }
}