package com.cantho.luanvan.dto.request;


import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private int ram;
    private int storage;
    private String cpu;
    private String screenSize;
    private String gpu;
    private BigDecimal price;
    private int stock;
    private boolean active;
    @Lob
    private String description;
    private Long brandId;
    private Long promotionId;
    private List<ImageDTO> imageDTOList = new ArrayList<>();
}
