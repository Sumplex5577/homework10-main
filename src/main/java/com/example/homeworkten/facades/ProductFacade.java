package com.example.homeworkten.facades;

import com.example.homeworkten.dtos.ProductDto;
import com.example.homeworkten.exceptions.NotFoundException;

import java.util.List;

public interface ProductFacade {
    ProductDto createProduct(ProductDto productDto)throws NotFoundException;;

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(ProductDto productDto) throws NotFoundException;

    ProductDto getById(ProductDto productDto) throws NotFoundException;

    List<ProductDto> getAllProducts();
}
