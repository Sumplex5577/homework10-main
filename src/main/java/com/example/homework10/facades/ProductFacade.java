package com.example.homework10.facades;

import com.example.homework10.dtos.ProductDto;
import com.example.homework10.exceptions.NotFoundException;

import java.util.List;

public interface ProductFacade {
    ProductDto createProduct(ProductDto productDto)throws NotFoundException;;

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(ProductDto productDto) throws NotFoundException;

    ProductDto getById(ProductDto productDto) throws NotFoundException;

    List<ProductDto> getAllProducts();
}
