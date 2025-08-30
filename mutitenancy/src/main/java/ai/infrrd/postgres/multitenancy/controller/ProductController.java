package ai.infrrd.postgres.multitenancy.controller;


import ai.infrrd.postgres.multitenancy.dto.ProductVO;
import ai.infrrd.postgres.multitenancy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all")
    public List<ProductVO> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(value = "/create")
    public ProductVO createProduct(@RequestBody ProductVO productVO) {
        return productService.save(productVO);
    }
}

