package com.cantho.luanvan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalPrice;
    private String note;
    private PaymentMethodType paymentMethodType;    // hinh thuc thanh toan
    private boolean paymentStatus;                  // donw hang da thanh toan chua ?
    private OrderStatus orderStatus;                // tinh trang cua don hang ?
    private LocalDateTime orderDate;
    private String shippingAddress;
}
