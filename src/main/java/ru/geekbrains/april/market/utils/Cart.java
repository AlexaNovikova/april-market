package ru.geekbrains.april.market.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.april.market.dtos.OrderItemDto;
import ru.geekbrains.april.market.models.OrderItem;
import ru.geekbrains.april.market.models.Product;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal sum;

    public Cart() {
        items = new ArrayList<>();
        sum = BigDecimal.ZERO;
    }

    public boolean addToCart(Long id) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void addToCart(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    private void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItemDto o : items) {
            sum = sum.add(o.getPrice());
        }
    }
}
