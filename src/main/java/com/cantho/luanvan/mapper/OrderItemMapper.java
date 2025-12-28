package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.OrderItemDTO;
import com.cantho.luanvan.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderItemMapper {
    OrderItem toEntity(OrderItemDTO orderItemDTO);

    @Mapping(source = "product.id", target = "productId")
    OrderItemDTO toDTO(OrderItem orderItem);
}
