package com.cantho.luanvan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private long id;
    private int totalItem;
    private BigDecimal totalPrice;
    private Long customerId;
    List<CartItemDTO> cartItemDTOS = new ArrayList<>();
}
