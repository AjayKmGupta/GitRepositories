package com.learn.mongocluster.controller;

import com.learn.mongocluster.entity.Product;
import com.learn.mongocluster.service.ProductService;
import com.learn.mongocluster.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductVO>> getAllProducts() {
        return new ResponseEntity<>( productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody ProductVO productVO) {
        productService.createProduct(productVO);
        return ResponseEntity.ok().build();
    }

}
