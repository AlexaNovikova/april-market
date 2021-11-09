package ru.geekbrains.april.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.april.market.models.Comment;
import ru.geekbrains.april.market.models.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
   public List<Comment> findAllByProduct (Product product);
}