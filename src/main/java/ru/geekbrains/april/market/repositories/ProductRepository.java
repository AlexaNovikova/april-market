package ru.geekbrains.april.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.april.market.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
