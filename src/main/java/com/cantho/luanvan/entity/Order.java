package com.cantho.luanvan.entity;

import com.cantho.luanvan.enums.OrderStatus;
import com.cantho.luanvan.enums.PaymentMethodType;
import jakarta.persistence.*;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalPrice;
    private String note;
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;    // hinh thuc thanh toan
    private boolean paymentStatus;                  // donw hang da thanh toan chua ?

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;                // tinh trang cua don hang ?
    private LocalDateTime orderDate;
    private String shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Review> reviews= new ArrayList<>();
}
