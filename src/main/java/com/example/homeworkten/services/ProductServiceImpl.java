package com.example.homeworkten.services;
import com.example.homeworkten.models.Product;
import com.example.homeworkten.repositories.ProductRepository;
import com.example.homeworkten.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ShopService shopService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ShopService shopService) {
        this.productRepository = productRepository;
        this.shopService = shopService;
    }

    @Override
    public Product createProduct(String name, BigDecimal price, Long idShop) throws NotFoundException {
        Product product = new Product(name,price);
        product.setShop(shopService.getShopById(idShop));
        shopService.getShopById(idShop).getProducts().add(product);
        productRepository.save(product);
        return product;
    }


    @Override
    public Product updateProduct(Long idProduct,String name, BigDecimal price, Long idShop){
        return productRepository.findById(idProduct)
                .map(entity -> {
                    entity.setName(name);
                    entity.setPrice(price);
                    try {
                        entity.setShop(shopService.getShopById(idShop));
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return productRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Not Found id = " + idProduct));
    }
    @Override
    public void deleteProduct(Long idProduct) throws NotFoundException {
        if (productRepository.existsById(idProduct)) {
            shopService.getShopById((productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException(idProduct.toString()))).getShop().getIdShop()).getProducts().remove(getById(idProduct));
            productRepository.deleteById(idProduct);
        } else {
            throw new NotFoundException("Product with ID #" + idProduct + " is not found");
        }
    }

    @Override
    public Product getById(Long idProduct) throws NotFoundException  {
        if (productRepository.findById(idProduct).isPresent()) {
            return productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException(idProduct.toString()));
        } else {
            throw new NotFoundException("Product with ID #" + idProduct + " is not found");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

}
