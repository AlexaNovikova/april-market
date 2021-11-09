package ru.geekbrains.april.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.april.market.models.Order;
import ru.geekbrains.april.market.models.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String description;
    private BigDecimal price;
    private List<Long> itemsId;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
        this.description = order.getItems().stream().map(o -> o.getProduct().getTitle() + " x" + o.getQuantity()).collect(Collectors.joining(", "));
        this.itemsId=order.getItems().stream().map(o->o.getProduct().getId()).collect(Collectors.toList());
    }

    public boolean hasProduct (Long id) {
        return itemsId.stream().anyMatch(o-> o.equals(id));
    }
}
