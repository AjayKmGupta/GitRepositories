package com.learning.postgres.multitenancy.mapper;

import com.learning.postgres.multitenancy.dto.ProductVO;
import com.learning.postgres.multitenancy.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductVO toDto(Product product);

    Product toEntity(ProductVO productVO);

}
