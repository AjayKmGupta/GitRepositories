package ai.infrrd.postgres.multitenancy.service;

import ai.infrrd.postgres.multitenancy.dto.ProductVO;
import ai.infrrd.postgres.multitenancy.entity.Product;
import ai.infrrd.postgres.multitenancy.mapper.ProductMapper;
import ai.infrrd.postgres.multitenancy.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts_Success() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0);

        ProductVO productVO = new ProductVO(1L, "Test Product", 100.0);

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toDto(product)).thenReturn(productVO);

        List<ProductVO> result = productService.getAllProducts();
        assertNotNull(result);
        assertEquals("Test Product", result.get(0).getName());
    }

    @Test
    void testAddProduct_Success() {
        ProductVO productVO = new ProductVO(null, "New Product", 0.1);
        Product product = new Product();
        product.setName("New Product");
        product.setPrice(0.1);

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("New Product");
        savedProduct.setPrice(0.1);

        ProductVO savedProductVO = new ProductVO(1L, "New Product", 0.1);

        when(productMapper.toEntity(productVO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productMapper.toDto(savedProduct)).thenReturn(savedProductVO);

        ProductVO result = productService.addProduct(productVO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
