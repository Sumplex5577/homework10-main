package com.example.homework10.services;

import com.example.homework10.exceptions.NotFoundException;
import com.example.homework10.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(String name, BigDecimal price, Long idShop)throws NotFoundException;;

    Product updateProduct(Long idProduct,String name, BigDecimal price, Long idShop);

    void deleteProduct(Long idProduct) throws NotFoundException;

    Product getById(Long idProduct) throws NotFoundException;

    List<Product> getAllProducts();
}
