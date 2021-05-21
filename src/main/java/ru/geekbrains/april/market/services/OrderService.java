package ru.geekbrains.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.models.Order;
import ru.geekbrains.april.market.models.OrderItem;
import ru.geekbrains.april.market.models.User;
import ru.geekbrains.april.market.repositories.OrderRepository;
import ru.geekbrains.april.market.utils.Cart;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createOrderForCurrentUser(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getTotalPrice());
        // todo распутать этот кусок
        order.setItems(cart.getItemsInCart());
        for (OrderItem oi : cart.getItemsInCart()) {
            oi.setOrder(order);
        }
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }
}
