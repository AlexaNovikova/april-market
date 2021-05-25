package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.dtos.CartDto;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.error_handling.MarketError;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.OrderItemsService;
import ru.geekbrains.april.market.utils.Cart;
import ru.geekbrains.april.market.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;
    private final OrderItemsService orderItemsService;

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable(name = "productId") Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/save")
    public void save() {
        orderItemsService.saveOrder(cart);
    }

    //вернуть всю корзину с общей стоимостью и количеством
    @GetMapping("/showProducts")
    public CartDto showProducts() {
        return new CartDto(cart);
    }


    //вернуть только результат
    @GetMapping("/clear")
    public void deleteAllProductsInCart() {
        cart.deleteAllItems();
    }

//    @GetMapping("/remove")
//    public void deleteOnrProductFromCart(@RequestParam Long id) {
//        cart.deleteOneProduct(id);
//    }
}
