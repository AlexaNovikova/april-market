package ru.geekbrains.april.market.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.utils.Cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
@NoArgsConstructor
public class CartDto {
    private List<ProductDto> items;
    private int totalPrice;
    private int itemsCount;

    public CartDto(Cart cart){
        items= new ArrayList<>();
        items.addAll(cart.getItemsInCart().stream().map(ProductDto::new).collect(Collectors.toList()));
        totalPrice=cart.getTotalPrice();
        itemsCount=cart.getItemsCount();
    }

    public List<ProductDto> showItemsInCart(){
        return Collections.unmodifiableList(items);
    }

    public int totalPrice() {
        return totalPrice;
    }

    public int itemsCount() {
        return itemsCount;
    }
}
