package ru.geekbrains.april.market.services;


import javassist.SerialVersionUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.models.OrderItem;
import ru.geekbrains.april.market.repositories.OrderItemRepository;
import ru.geekbrains.april.market.utils.Cart;

import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemsService{
    private final OrderItemRepository orderItemRepository;

    public void saveOrder(Cart cart){
        List<OrderItem> orderItemList =cart.getItemsInCart();
        for (OrderItem item: orderItemList) {
            orderItemRepository.save(item);
        }
    }
}
