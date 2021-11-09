package ru.geekbrains.april.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.repositories.CategoryRepository;
import ru.geekbrains.april.market.repositories.ProductRepository;
import ru.geekbrains.april.market.repositories.specifications.ProductSpecifications;
import ru.geekbrains.april.market.services.ProductService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.peer.PanelPeer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getAllProducts() throws Exception {
        List<Product> allProducts= new ArrayList<>();
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");
        for(int i=0; i<10; i++) {
            Product product = new Product();
            product.setId((long)i);
            product.setTitle("Product "+ i );
            product.setPrice(new BigDecimal(200*i));
            product.setCategory(category);
            allProducts.add(product);
            System.out.println(product);
        }
        Page<Product> page = new PageImpl<>(allProducts);
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();

        Specification<Product> spec = ProductSpecifications.build(requestParams);
        given(productRepository.findAll(spec, PageRequest.of(0,10))).willReturn(page);

        mvc.perform(get("/api/v1/products/")
                .params(requestParams)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(10)))
                .andExpect(jsonPath("$.content[0].title", is(allProducts.get(0).getTitle())));

    }
}
