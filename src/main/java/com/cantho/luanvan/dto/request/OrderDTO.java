package com.cantho.luanvan.dto.request;

import com.cantho.luanvan.enums.OrderStatus;
import com.cantho.luanvan.enums.PaymentMethodType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long id;
    private String note;
    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private String paymentMethodType;
    private boolean paymentStatus;
    private String shippingAddress;
    private BigDecimal totalPrice;
    private int totalQuantity;
    private long customerId;

    private List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
}
