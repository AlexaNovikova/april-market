package ru.geekbrains.april.market;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.security.core.parameters.P;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.models.Role;
import ru.geekbrains.april.market.services.ProductService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> jackson;

    @Autowired
    private  JacksonTester<Product> productJacksonTester;


    @Test
    public void jsonSerializationTest() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");
        // {
        //   "id": 1,
        //   "name": "USER"
        // }

        Product product = new Product();
        product.setTitle("Coffee");
        product.setPrice(new BigDecimal(100));
        product.setId(1L);
        Category category = new Category();
        category.setTitle("Food");
        category.setId(1L);
        product.setCategory(category);
        assertThat(productJacksonTester.write(product))
                .hasJsonPathNumberValue("$.id")
                .hasJsonPath("category", Category.class)
                .extractingJsonPathStringValue("$.title").isEqualTo("Coffee");


        assertThat(jackson.write(role))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.name").isEqualTo("USER");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"name\":\"ADMIN\"}";
        Role realRole = new Role();
        realRole.setId(2L);
        realRole.setName("ADMIN");

        assertThat(jackson.parse(content)).isEqualTo(realRole);
        assertThat(jackson.parseObject(content).getName()).isEqualTo("ADMIN");
    }
}