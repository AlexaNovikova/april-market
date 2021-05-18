package ru.geekbrains.april.market.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.april.market.models.OrderItem;
import ru.geekbrains.april.market.models.Product;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private String productTitle;
    private Long id;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public OrderItemDto(OrderItem orderItem){
        this.id=orderItem.getProduct().getId();
        this.productTitle=orderItem.getProduct().getTitle();
        this.quantity=orderItem.getQuantity();
        this.pricePerProduct=orderItem.getPricePerProduct();
        this.price=orderItem.getPrice();
    }
}
