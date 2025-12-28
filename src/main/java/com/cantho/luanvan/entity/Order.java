package com.cantho.luanvan.entity;

import com.cantho.luanvan.enums.OrderStatus;
import com.cantho.luanvan.enums.PaymentMethodType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private int totalQuantity;
    private String note;
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;    // hinh thuc thanh toan
    private boolean paymentStatus;// donw hang da thanh toan chua ?
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;                // tinh trang cua don hang ?
    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime orderDate;
    private String shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Review> reviews= new ArrayList<>();

    // them order-item vao order va tinh lai tong gia tri order
    public void addItem(Product product, int quantity, BigDecimal priceBuy) {
        OrderItem item = OrderItem.builder()
                .order(this)
                .product(product)
                .price(priceBuy)
                .quantity(quantity)
                .build();
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<>();
        }
        this.orderItems.add(item);
        this.totalQuantity = this.totalQuantity + quantity;
        this.totalPrice = this.totalPrice.add(
                priceBuy.multiply(BigDecimal.valueOf(quantity))
        );
        product.decreaseStock(quantity);
    }

    // check xem customer do co phai la nguoi cua don hang nay khong
    public boolean isOwnedById(long customerId){
        return  this.getCustomer().getId().equals(customerId);
    }

    // check product co trong order khong ?
    public boolean containsProduct(Long productId){
        return orderItems.stream()
                .anyMatch(item -> item.getProduct().getId().equals(productId));
    }

    // check trang thai don hang da completed chua ?
    public boolean isCompleted(){
        return this.orderStatus == OrderStatus.COMPLETED;
    }

}
