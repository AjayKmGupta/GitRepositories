package ai.infrrd.postgres.multitenancy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import ai.infrrd.postgres.multitenancy.dto.ProductVO;
import ai.infrrd.postgres.multitenancy.entity.Product;
import ai.infrrd.postgres.multitenancy.exceptions.ProductNotFoundException;
import ai.infrrd.postgres.multitenancy.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetAllProducts_Success() throws Exception {
        List<ProductVO> products = List.of(
                new ProductVO(1L, "Test Product", 100.0)
        );
        Mockito.when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"))
                .andExpect(jsonPath("$[0].price").value(100.0));
    }

    @Test
    public void testGetAllProducts_NotFound() throws Exception {
        Mockito.when(productService.getAllProducts()).thenThrow(new ProductNotFoundException("Product not found"));

        mockMvc.perform(get("/products/all"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddProduct_Success() throws Exception {
        Product product = new Product(2L, "New Product", 150.0);
        ProductVO savedProduct = new ProductVO(2L, "New Product", 150.0);
        Mockito.when(productService.addProduct(Mockito.any(ProductVO.class))).thenReturn(savedProduct);

        mockMvc.perform(post("/products/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Product\",\"price\":150.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("New Product"))
                .andExpect(jsonPath("$.price").value(150.0));
    }

    @Test
    public void testAddProduct_Exception() throws Exception {
        Mockito.when(productService.addProduct(Mockito.any(ProductVO.class)))
                .thenThrow(new RuntimeException("Error adding product"));

        mockMvc.perform(post("/products/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Product\",\"price\":150.0}"))
                .andExpect(status().isInternalServerError());
    }
}
