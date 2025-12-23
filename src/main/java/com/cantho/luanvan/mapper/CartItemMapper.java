package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.CartItemDTO;
import com.cantho.luanvan.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CartItemMapper {
    CartItem toEntity(CartItemDTO cartItemDTO);

    @Mapping(source = "product", target = "productDTO")
    CartItemDTO toDTO(CartItem cartItem);
}
