package com.cantho.luanvan.service.application;

import com.cantho.luanvan.dto.request.OrderDTO;
import com.cantho.luanvan.entity.Order;
import com.cantho.luanvan.mapper.OrderMapper;
import com.cantho.luanvan.service.domain.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderApplicationService {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO){
        // init order
        Order order = orderService.initOrder(orderDTO);

        // add item order
        orderService.addItem(order, orderDTO.getOrderItemDTOS());

        // save order
        Order saved = orderService.save(order);

        // Order -> OrderDTO
        return orderMapper.toDTO(saved);
    }
}
