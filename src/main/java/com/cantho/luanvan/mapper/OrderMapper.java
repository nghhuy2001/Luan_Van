package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.OrderDTO;
import com.cantho.luanvan.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);

    @Mapping(source = "orderItems", target = "orderItemDTOS")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "totalQuantity" , target = "totalQuantity")
    OrderDTO toDTO(Order order);
}
