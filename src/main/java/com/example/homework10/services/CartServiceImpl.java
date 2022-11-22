package com.example.homework10.services;

import com.example.homework10.models.Cart;
import com.example.homework10.models.Product;
import com.example.homework10.repositories.CartRepository;
import com.example.homework10.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@Slf4j
public class CartServiceImpl implements CartService{

    private final PersonService personService;
    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartServiceImpl(PersonService personService, CartRepository cartRepository, ProductService productService) {
        this.personService = personService;
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public Cart createCartByPersonId(Long idPerson) throws NotFoundException {
        Cart cart = new Cart(personService.getPersonById(idPerson));
        personService.getPersonById(idPerson).getCarts().add(cart);
        log.debug("createCartByPersonId() - start: Person id = {}", idPerson);
        cartRepository.save(cart);
        log.debug("createCartByPersonId() - end: Cart id = {}", cart.getIdCart());
        return cart;
    }


    @Override
    public Cart addProductByProductIdAndCartId(Long idCart, Long idProduct) throws NotFoundException {
        Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new NotFoundException(idCart.toString()));
        Product product = productService.getById(idProduct);
        if (cartRepository.findById(idCart).isPresent()) {
            cart.getProducts().add(product);
            BigDecimal sum = cart.getSum().add(productService.getById(idProduct).getPrice());
            cart.setSum(sum);
            log.debug("addProductByProductIdAndCartId() - start: Cart id={}, Product id = {}", idCart, idProduct);
            cartRepository.save(cart);
            log.debug("addProductByProductIdAndCartId() - end: Cart id={}, Product id = {}", idCart, idProduct);
            return cart;
        } else {
            throw new NotFoundException("Cart with ID #" + idCart + " is not found");
        }
    }

    @Override
    public Cart removeProductByProductIdAndCartId(Long idCart, Long idProduct) throws NotFoundException {
        Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new NotFoundException(idCart.toString()));
        Product product = productService.getById(idProduct);
        if (cartRepository.findById(idCart).isPresent()) {
            log.debug("removeProductByProductIdAndCartId() - start: Cart id={}, Product id = {}", idCart, idProduct);
            cart.getProducts().remove(product);
            if (cart.getSum().compareTo(new BigDecimal("0.0")) != 0) {
                BigDecimal sum = cart.getSum().subtract(productService.getById(idProduct).getPrice());
                cart.setSum(sum);
                cartRepository.save(cart);
                log.debug("removeProductByProductIdAndCartId() - end: Cart id={}, Product id = {}", idCart, idProduct);
            } else {
                cart.setSum(BigDecimal.valueOf(0.0));
            }
            cartRepository.save(cart);
            return cart;
        } else {
            throw new NotFoundException("Cart with ID #" + idCart + " is not found");
        }
    }

    @Override
    public void removeAllProductsFromCartById(Long idCart)throws NotFoundException {
        if (cartRepository.findById(idCart).isPresent()) {
            Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new NotFoundException(idCart.toString()));
            cart.getProducts().clear();
            cart.setSum(new BigDecimal("0.00"));
            log.debug("removeAllProductsFromCartById() - start: Cart id={}", idCart);
            cartRepository.save(cart);
            log.debug("removeAllProductsFromCartById() - end: Cart id={},", idCart);
        } else {
            throw new NotFoundException("Cart with ID #" + idCart + " is empty");
        }
    }

    @Override
    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long idCart) throws NotFoundException {
        if (cartRepository.findById(idCart).isPresent()) {
            return cartRepository.findById(idCart).orElseThrow(() -> new NotFoundException(idCart.toString()));
        } else {
            throw new NotFoundException("Cart with ID #" + idCart + " is not found");
        }
    }

    @Override
    public void removeCartById(Long idCart) throws NotFoundException {
        if (cartRepository.findById(idCart).isPresent()) {
            Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new NotFoundException(idCart.toString()));
            removeAllProductsFromCartById(idCart);
            personService.getPersonById(cart.getPerson().getIdPerson()).getCarts().remove(cart);
            cartRepository.deleteById(idCart);
        } else {
            throw new NotFoundException("Cart with ID #" + idCart + "is not found");
        }
    }



}

