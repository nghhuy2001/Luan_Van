package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.OrderDTO;
import com.cantho.luanvan.dto.request.OrderItemDTO;
import com.cantho.luanvan.entity.Customer;
import com.cantho.luanvan.entity.Order;
import com.cantho.luanvan.entity.OrderItem;
import com.cantho.luanvan.entity.Product;
import com.cantho.luanvan.enums.OrderStatus;
import com.cantho.luanvan.enums.PaymentMethodType;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.OrderMapper;
import com.cantho.luanvan.repository.CustomerRepository;
import com.cantho.luanvan.repository.OrderRepository;
import com.cantho.luanvan.repository.ProductRepository;
import com.cantho.luanvan.service.domain.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order initOrder(OrderDTO orderDTO) {
        Customer customer = customerRepository.getCustomerById(orderDTO.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy khách hàng có id: "+ orderDTO.getCustomerId())
        );


        return Order.builder()
                .note(orderDTO.getNote())
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.PENDING)
                .paymentMethodType(PaymentMethodType.valueOf(orderDTO.getPaymentMethodType())) // sau này thay cái này từ dto nhận từ fe
                .paymentStatus(false)
                .totalQuantity(0)
                .shippingAddress(orderDTO.getShippingAddress())
                .totalPrice(BigDecimal.ZERO)
                .customer(customer)
                .build();
    }

    @Override
    public void addItem(Order order, List<OrderItemDTO> orderItemsDTO) {
        if (orderItemsDTO.isEmpty()) {
            throw new RuntimeException("Đơn hàng phải có ít nhất 1 sản phẩm");
        }

        for (OrderItemDTO dto : orderItemsDTO) {
            Product product = productRepository.getProductById(dto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy sản phẩm với id: " + dto.getProductId()
                    ));
            if (dto.getQuantity() <= 0) {
                throw new RuntimeException("Số lượng phải > 0");
            }

            order.addItem(product, dto.getQuantity(), product.getPrice());
        }
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Page<OrderDTO> getOrderAllOrder(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toDTO);
    }
}
