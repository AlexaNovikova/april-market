package ru.geekbrains.april.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.OrderItem;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.repositories.ProductRepository;
import ru.geekbrains.april.market.utils.Cart;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final Cart cart;

    public void addToCart(Long id) {
        for (OrderItem orderItem : cart.getItemsInCart()) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        OrderItem item = new OrderItem(product);
        cart.add(item);
        recalculate();
    }

    public void recalculate(){
        cart.setTotalPrice(BigDecimal.ZERO);
        for(OrderItem oi:cart.getItemsInCart()){
            cart.setTotalPrice(cart.getTotalPrice().add(oi.getPrice()));
        }
    }


    public void deleteAllItems() {
        cart.getItemsInCart().clear();
        recalculate();
    }

  public void clear(){
        cart.getItemsInCart().clear();
        recalculate();
  }

    public Cart getCart() {
        return cart;
    }
}
