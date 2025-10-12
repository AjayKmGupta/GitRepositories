package com.learn.mongocluster.mapper;

import com.learn.mongocluster.entity.Product;
import com.learn.mongocluster.vo.ProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring" )
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductVO toVO(Product product);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    Product toEntity(ProductVO productVO);
}
