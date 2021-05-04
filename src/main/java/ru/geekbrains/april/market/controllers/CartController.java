package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.april.market.error_handling.MarketError;
import ru.geekbrains.april.market.models.Product;
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
    private final ProductService productService;

    @GetMapping("/add")
    public ResponseEntity<?> add(@RequestParam Long id) {
        Optional<Product> product = productService.findOneById(id);
        if (product.isPresent()) {
            Product productAddToCart = product.get();
            cart.add(productAddToCart);
            return new ResponseEntity<>(productAddToCart, HttpStatus.OK);
        } else {
            List<String> errors = new ArrayList<>();
            errors.add("Product not found");
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/showProducts")
    public ResponseEntity<?> showProducts() {
        List<Product> productsInCart = cart.showItemsInCart();
        return new ResponseEntity<>(productsInCart, HttpStatus.OK);
    }

    @GetMapping("/deleteAll")
    public ResponseEntity<?> deleteAllProductsInCart() {
        cart.deleteAllItems();
        List<Product> productsInCart = cart.showItemsInCart();
        return new ResponseEntity<>(productsInCart, HttpStatus.OK);
    }

    @GetMapping("/remove")
    public ResponseEntity<?> deleteOnrProductFromCart(@RequestParam Long id) {
        cart.deleteOneProduct(id);
        List<Product> productsInCart = cart.showItemsInCart();
        return new ResponseEntity<>(productsInCart, HttpStatus.OK);
    }
}
