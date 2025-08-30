package ai.infrrd.postgres.multitenancy.service;


import ai.infrrd.postgres.multitenancy.dto.ProductVO;
import ai.infrrd.postgres.multitenancy.entity.Product;
import ai.infrrd.postgres.multitenancy.mapper.ProductMapper;
import ai.infrrd.postgres.multitenancy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    public List<ProductVO> getAllProducts() {

        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductVO addProduct(ProductVO productVO) {
        Product product = productMapper.toEntity(productVO);
        return productMapper.toDto(productRepository.save(product));
    }
}
