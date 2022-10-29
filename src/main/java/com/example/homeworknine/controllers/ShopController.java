package com.example.homeworknine.controllers;

import com.example.homeworknine.exceptions.NotFoundException;

import com.example.homeworknine.dtos.ShopDto;
import com.example.homeworknine.services.ShopService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import static com.example.homeworknine.converters.ShopConverter.convertShopDtoToShop;
import static com.example.homeworknine.converters.ShopConverter.convertShopToShopDto;

@Controller
@RequestMapping(path="/shop")
@Slf4j
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String shopIndex(Model model) {
        String message = "Shop start page";
        model.addAttribute("message", message);
        return "/shop/shopIndex";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createShopView(Model model) {
        model.addAttribute("shop", new ShopDto());
        return "/shop/createShop";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createShop(@ModelAttribute("shop") ShopDto shopDto) {
        shopService.createShop(convertShopDtoToShop(shopDto));
        log.info("New shop is created with name [{}]", shopDto.getName());
        return "/shop/createShopSuccess";
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getShopByIdView(Model model) {
        model.addAttribute("shopById", new ShopDto());
        return "/shop/getShop";
    }
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getShopById(@ModelAttribute("shopById") ShopDto shopDto, Model model) throws NotFoundException {
        ShopDto shopById = convertShopToShopDto(shopService.getShopById(shopDto.getIdShop()));
        model.addAttribute("shopById", shopById);
        return "/shop/getShopSuccess";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteShopByIdView(Model model) {
        model.addAttribute("shop", new ShopDto());
        return "/shop/deleteShop";
    }
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteShop(@ModelAttribute("shop") ShopDto shopDto) throws NotFoundException {
        shopService.deleteShop(shopDto.getIdShop());
        log.info("Shop with name [{}} and ID [{}] is deleted", shopDto.getName(), shopDto.getIdShop());
        return "/shop/deleteShopSuccess";
    }
    @GetMapping( "/all")
    public String getAllShops(Model model) {
        model.addAttribute("all", shopService.getAllShops());
        return "/shop/allShops";
    }
}
