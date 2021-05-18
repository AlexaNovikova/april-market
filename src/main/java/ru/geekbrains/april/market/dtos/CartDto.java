package ru.geekbrains.april.market.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.utils.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;


    public CartDto(Cart cart){
        items= new ArrayList<>();
        items.addAll(cart.getItemsInCart().stream().map(OrderItemDto::new).collect(Collectors.toList()));
        totalPrice=cart.getTotalPrice();
    }

}
