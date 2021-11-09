package ru.geekbrains.april.market;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.repositories.CategoryRepository;
import ru.geekbrains.april.market.repositories.ProductRepository;
import ru.geekbrains.april.market.repositories.specifications.ProductSpecifications;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void categoryRepositoryTest() {
        Category category = new Category();
        category.setTitle("Books");
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categoriesList = categoryRepository.findAll();

        Assertions.assertEquals(2, categoriesList.size());
        Assertions.assertEquals("Books", categoriesList.get(1).getTitle());
    }

    @Test
    public void productsRepositoryTest() {
        List<Product> productsList = productRepository.findAll();
        Assertions.assertEquals(25,productsList.size());
        Assertions.assertEquals("Coffee", productsList.get(1).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Category> categoriesList = categoryRepository.findAll();
        Assertions.assertEquals(1, categoriesList.size());
    }
}
