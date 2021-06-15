package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.services.CartService;
import ru.geekbrains.april.market.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    @GetMapping("/add")
    public void addToCart(@RequestParam(name = "prodId") Long id, @RequestParam String cartName) {
        cartService.addToCart(cartName, id);
    }
//
//    @GetMapping("/save")
//    public void save() {
//        orderItemsService.saveOrder(cartService.getCart());
//    }

    //вернуть всю корзину с общей стоимостью и количеством
    @GetMapping("/showProducts")
    public Cart getCart(@RequestParam String cartName) {
        return cartService.getCurrentCart(cartName);
    }


    //вернуть только результат
    @GetMapping("/clear")
    public void clearCart(@RequestParam String cartName) {
        cartService.clearCart(cartName);
    }
//    @GetMapping("/remove")
//    public void deleteOnrProductFromCart(@RequestParam Long id) {
//        cart.deleteOneProduct(id);
//    }
}
