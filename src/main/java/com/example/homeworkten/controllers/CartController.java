package com.example.homeworkten.controllers;


import com.example.homeworkten.exceptions.NotFoundException;
import com.example.homeworkten.converters.CartConverter;
import com.example.homeworkten.dtos.CartDto;
import com.example.homeworkten.dtos.PersonDto;
import com.example.homeworkten.facades.CartFacade;
import com.example.homeworkten.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(path="/cart")
public class CartController {

    private final CartFacade cartFacade;

    public CartController(CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String cartIndex(Model model) {
        String message = "Cart control page";
        model.addAttribute("message", message);
        return "/cart/cartIndex";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCartView(Model model){
        model.addAttribute("person", new PersonDto());
        model.addAttribute("cart", new CartDto());
        return "/cart/createCart";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCart(@ModelAttribute("person") PersonDto personDto) throws NotFoundException {
        cartFacade.createCartByPersonId(personDto);
        log.info("New cart for person with ID [{}] is created", personDto.getIdPerson());
        return "/cart/createCartSuccess";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProductView(Model model) {
        model.addAttribute("cart", new CartDto());
        return "/cart/addProductToCart";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.PUT, RequestMethod.POST})
    public String addProductByProductIdAndCartId(@ModelAttribute("cart") CartDto cartDto) throws NotFoundException {
        cartFacade.addProductByProductIdAndCartId(cartDto);
        log.info("Product with ID [{}] is added to cart with ID [{}]", cartDto.getIdProduct(),cartDto.getIdCart());
        return "/cart/addProductToCartSuccess";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCartView(Model model) {
        model.addAttribute("cart", new CartDto());
        return "/cart/deleteCart";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteCart(@ModelAttribute("cart") CartDto cartDto) throws NotFoundException {
        cartFacade.removeCartById(cartDto);
        log.info("Cart with ID [{}] is deleted", cartDto.getIdCart());
        return "/cart/deleteCartSuccess";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllCarts(Model model) {
        model.addAttribute("all", cartFacade.getAllCarts());
        return "/cart/allCarts";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getCartByIdView(Model model) {
        model.addAttribute("cartById", new CartDto());
        return "/cart/getCart";
    }
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getCartById(@ModelAttribute("cartById") CartDto cartDto, Model model) throws NotFoundException {
        CartDto cartById = cartFacade.getCartById(cartDto);
        model.addAttribute("cartById", cartById);
        log.info("Cart with ID [{}] is gotten", cartDto.getIdCart());
        return "/cart/getCartSuccess";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeProductByProductIdAndCartIdView(Model model) {
        model.addAttribute("cart", new CartDto());
        return "/cart/removeProductFromCart";
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.PUT, RequestMethod.POST})
    public String removeProductByProductIdAndCartId(@ModelAttribute("cart") CartDto cartDto) throws NotFoundException {
        cartFacade.removeProductByProductIdAndCartId(cartDto);
        log.info("Product with ID [{}] is deleted from cart with ID [{}]", cartDto.getIdProduct(),cartDto.getIdCart());
        return "/cart/removeProductFromCartSuccess";
    }

    @RequestMapping(value = "/clean", method = RequestMethod.GET)
    public String removeAllProductsFromCartByIdView(Model model) {
        model.addAttribute("cart", new CartDto());
        return "/cart/cleanCart";
    }

    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    public String removeAllProductsFromCartById(@ModelAttribute("cart") CartDto cartDto) throws NotFoundException {
        cartFacade.removeAllProductsFromCartById(cartDto);
        log.info("Cart with ID [{}] is cleaned]", cartDto.getIdCart());
        return "/cart/cleanCartSuccess";
    }
}

