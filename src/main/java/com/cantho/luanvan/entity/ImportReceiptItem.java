package com.cantho.luanvan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportReceiptItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantityImport;
    @Column(name = "unit_price", precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "import_receipt_id", referencedColumnName = "id")
    private ImportReceipt importReceipt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

}
