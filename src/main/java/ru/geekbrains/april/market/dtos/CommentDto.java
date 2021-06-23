package ru.geekbrains.april.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.april.market.models.Comment;
import ru.geekbrains.april.market.models.Product;

@Data
@NoArgsConstructor
public class CommentDto {
    private String text;
    private String username;

    public CommentDto(Comment comment) {
        this.text = comment.getComment();
        this.username = comment.getUsername();
    }
}
