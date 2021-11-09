package ru.geekbrains.april.market;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.CartService;
import ru.geekbrains.april.market.services.ProductService;
import ru.geekbrains.april.market.utils.Cart;

import java.util.Optional;

@SpringBootTest
public class CartTest {

    @MockBean
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Test
    public void cartFillingTest() {
        Cart cart = new Cart();
        Product p = productService.findOneById(1L).get();
        cart.addToCart(p);
        cart.addToCart(p.getId());
        Mockito.doReturn(cart)
                .when(cartService)
                .getCurrentCart("cart");
        Assertions.assertEquals(1, cartService.getCurrentCart("cart").getItems().size());
        cart.clear();
        Assertions.assertEquals(0, cartService.getCurrentCart("cart").getItems().size());
    }
}