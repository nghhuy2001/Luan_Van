package com.cantho.luanvan.entity;

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

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime importDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "importReceipt")
    private List<ImportReceiptItem> importReceiptItems = new ArrayList<>();

    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    public void sumTotalPrice(BigDecimal unitPrice, int quantity) {
        if (unitPrice == null || quantity <= 0) return;

        if (this.totalPrice == null) {
            this.totalPrice = BigDecimal.ZERO;
        }

        BigDecimal itemTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        this.totalPrice = this.totalPrice.add(itemTotal);
    }



}