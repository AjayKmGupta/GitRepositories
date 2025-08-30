package ai.infrrd.postgres.multitenancy.controller;


import ai.infrrd.postgres.multitenancy.dto.ProductVO;
import ai.infrrd.postgres.multitenancy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductVO>> getProducts() {
        return new ResponseEntity<>( productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ProductVO> createProduct(@RequestBody ProductVO productVO) {
        return new ResponseEntity<>( productService.addProduct(productVO), HttpStatus.CREATED );
    }
}

