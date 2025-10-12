package com.learn.mongocluster.service;

import com.learn.mongocluster.entity.Product;
import com.learn.mongocluster.mapper.ProductMapper;
import com.learn.mongocluster.repository.ProductRepository;
import com.learn.mongocluster.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductVO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toVO).toList();
    }

    public void createProduct(ProductVO productVO) {
        productRepository.insert(productMapper.toEntity(productVO));
    }
}