package ru.geekbrains.april.market;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.models.Order;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.models.User;
import ru.geekbrains.april.market.repositories.OrderRepository;
import ru.geekbrains.april.market.repositories.UserRepository;
import ru.geekbrains.april.market.services.CartService;
import ru.geekbrains.april.market.services.OrderService;
import ru.geekbrains.april.market.services.ProductService;
import ru.geekbrains.april.market.utils.Cart;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @MockBean
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @Test
    @Transactional
    public void createOrderTest(){
        Cart cart = new Cart();
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Bread");
        product.setPrice(new BigDecimal(20));
        Category category= new Category();
        category.setId(1L);
        category.setTitle("Food");
        product.setCategory(category);
        cart.addToCart(product);
        Mockito.doReturn(cart)
                .when(cartService)
                .getCurrentCart("cart");
        Mockito.doReturn(Optional.of(product))
                .when(productService)
                .findOneById(1l);
        User user = userRepository.findByUsername("user").get();
        user.getRoles();
        Order order = orderService.createOrderForCurrentUser(user);
        Assertions.assertEquals(orderRepository.findAllByUser(user).get(0).getItems().size(),1);
        Assertions.assertEquals(orderRepository.findAllByUser(user).get(0).getItems().get(0).getProduct().getTitle(), "Bread");
        Assertions.assertEquals(order, orderRepository.findAllByUser(user).get(0));
    }
}
