package ru.geekbrains.april.market.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
@Data
@NoArgsConstructor
public class Category {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @OneToMany(mappedBy="category")
    private List<Product> products;
}
