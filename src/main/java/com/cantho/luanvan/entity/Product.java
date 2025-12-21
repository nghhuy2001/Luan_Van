package com.cantho.luanvan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int ram;
    private int storage;
    private String cpu;
    private String screenSize;
    private String gpu;
    @Lob
    private String description;
    private BigDecimal price;
    private int stock;

    // san pham duoc tao nhung co the chua dua ra ban hoac khong ban sp nay nua
    // xoa soft
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    private Promotion promotion;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ImportReceiptItem> importReceiptItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.stock += quantity;
    }

}
