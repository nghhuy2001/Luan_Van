package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.CartDTO;
import com.cantho.luanvan.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    Cart toEntity(CartDTO cartDTO);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "cartItems", target = "cartItemDTOS")
    @Mapping(source = "totalItems", target = "totalItem")
    CartDTO toDTO (Cart cart);
}
