package com.example.homework10.services;
import com.example.homework10.models.Shop;
import com.example.homework10.repositories.ShopRepository;
import com.example.homework10.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ShopServiceImpl implements ShopService{
    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);

    }

    @Override
    public void deleteShop(Long idShop) throws NotFoundException {
        if (shopRepository.existsById(idShop)) {
            shopRepository.deleteById(idShop);
        } else {
            throw new NotFoundException("Shop with ID #" + idShop + " is not found");
        }
    }

    @Override
    public Shop getShopById(Long idShop) throws NotFoundException {
        if (shopRepository.findById(idShop).isPresent()) {
            return shopRepository.findById(idShop).orElseThrow(() -> new NotFoundException(idShop.toString()));
        } else {
            throw new NotFoundException("Shop with ID #" + idShop + " is not found");
        }
    }

    @Override
    public List<Shop> getAllShops() {
        return (List<Shop>) shopRepository.findAll();
    }
}
