package ru.geekbrains.april.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.april.market.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
