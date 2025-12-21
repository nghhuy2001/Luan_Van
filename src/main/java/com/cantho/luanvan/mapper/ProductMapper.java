package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.ProductDTO;
import com.cantho.luanvan.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO productDTO);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "promotion.id", target = "promotionId")
    @Mapping(source = "images", target = "imageDTOList")
    ProductDTO toDTO(Product product);
}
