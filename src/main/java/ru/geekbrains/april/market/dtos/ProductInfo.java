package ru.geekbrains.april.market.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.april.market.models.Comment;
import ru.geekbrains.april.market.models.Product;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductInfo {

    private ProductDto productDto;
    private List<CommentDto> comments;

    public ProductInfo(Product product){
        productDto = new ProductDto(product);
        comments=product.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
    }
}
