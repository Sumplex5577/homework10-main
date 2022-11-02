package com.example.homeworkten.controllers;

import com.example.homeworkten.exceptions.NotFoundException;
import com.example.homeworkten.dtos.ProductDto;
import com.example.homeworkten.facades.ProductFacade;
import com.example.homeworkten.services.ProductService;
import com.example.homeworkten.converters.ProductConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product")
@Slf4j
public class ProductController {

    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String productIndex(Model model) {
        String message = "Product control page";
        model.addAttribute("message", message);
        return "/product/productIndex";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProductView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "/product/createProduct";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") ProductDto productDto) throws NotFoundException {
        productFacade.createProduct(productDto);
        log.info("New product was created with name [{}] and price [{}]", productDto.getName(), productDto.getPrice());
        return "/product/createProductSuccess";
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getProductByIdView(Model model) {
        model.addAttribute("productById", new ProductDto());
        return "/product/getProduct";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getProductById(@ModelAttribute("productById") ProductDto productDto, Model model) throws NotFoundException {
        ProductDto productById = productFacade.getById(productDto);
        model.addAttribute("productById", productById);
        log.info("Product with ID [{}] was found", productDto.getIdProduct());
        return "/product/getProductSuccess";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateProductView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "/product/updateProduct";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") ProductDto productDto) {
        productFacade.updateProduct(productDto);
        log.info("Product with ID [{}] was updated", productDto.getIdProduct());
        return "/product/updateProductSuccess";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProductView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "/product/deleteProduct";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteProduct(@ModelAttribute("product") ProductDto productDto) throws NotFoundException {
        productFacade.deleteProduct(productDto);
        log.info("Product with ID [{}] is deleted", productDto.getIdProduct());
        return "/product/deleteProductSuccess";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("all", productFacade.getAllProducts());
        return "/product/allProducts";
    }

}

