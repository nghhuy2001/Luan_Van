package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.OrderDTO;
import com.cantho.luanvan.dto.request.OrderItemDTO;
import com.cantho.luanvan.entity.Order;
import com.cantho.luanvan.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order initOrder(OrderDTO orderDTO);
    void addItem(Order order , List<OrderItemDTO> orderItemsDTO);
    Order save(Order order);

    Page<OrderDTO> getOrderAllOrder(Pageable pageable);
}
