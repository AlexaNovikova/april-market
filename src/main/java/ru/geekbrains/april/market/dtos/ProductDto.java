package ru.geekbrains.april.market.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.april.market.models.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ProductDto {
    private  Long id;

    @Size(min = 4, max = 255, message="Title size: 4-255")
    private String title;

    private String categoryTitle;

    @Min(value = 1, message ="Min price = 1")
    private int price;

    public ProductDto(Product product){
        this.id=product.getId();
        this.title=product.getTitle();
        this.categoryTitle=product.getCategory().getTitle();
        this.price=product.getPrice();
    }
}
