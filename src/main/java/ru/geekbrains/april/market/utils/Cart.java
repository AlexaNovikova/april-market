package ru.geekbrains.april.market.utils;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.april.market.models.OrderItem;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


// Не уверена, что сделано верно. Как присваивать  serialVersionUID классам (Entity)? Нужно ли? Ведь
// тогда нужно вносить дополнительное поле?

//Не поняла как при выходе из учетной записи , сделать так, чтобы содержимое корзины было пустым?



@Data
@Component
@RequiredArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable{

    private static final long serialVersionUID = 820377086046398299L;
    private ArrayList<OrderItem> items;
    private BigDecimal totalPrice;

    @PostConstruct
    public void init(){
        items=new ArrayList<>();
        totalPrice=BigDecimal.ZERO;
    }

    public List<OrderItem> getItemsInCart() {
      return Collections.unmodifiableList(items);
    }


//    public void deleteOneProduct(Long id) {
//        Optional<Product> product = items.stream().filter((p) -> p.getId().equals(id)).findAny();
//        product.ifPresent(value -> items.remove(value));
//        totalPrice=totalPrice.subtract(product.get().getPrice());
//        itemsCount--;
//    }

   public void add(OrderItem orderItem){
        items.add(orderItem);
   }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

}
