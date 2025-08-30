package ai.infrrd.postgres.multitenancy.mapper;

import ai.infrrd.postgres.multitenancy.dto.ProductVO;
import ai.infrrd.postgres.multitenancy.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductVO toDto(Product product);

    Product toEntity(ProductVO productVO);

}
