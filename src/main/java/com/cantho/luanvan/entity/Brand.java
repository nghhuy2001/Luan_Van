package com.cantho.luanvan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean active; // khi an thuong hieu => an luon san pham cua no
    @OneToMany(mappedBy = "brand", fetch =  FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
