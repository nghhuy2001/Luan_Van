package com.cantho.luanvan.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;

    @NotBlank(message = "Nội dung đánh giá không được để trống")
    @Size(min = 5, max = 1000, message = "Nội dung đánh giá từ 5 đến 1000 ký tự")
    private String content;

    @Min(value = 1, message = "Rating tối thiểu là 1")
    @Max(value = 5, message = "Rating tối đa là 5")
    private int rating;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime createdAt;

    @NotNull(message = "productId không được null")
    @Positive(message = "productId phải là số dương")
    private Long productId;

    @NotNull(message = "orderId không được null")
    @Positive(message = "orderId phải là số dương")
    private Long orderId;

    @NotNull(message = "customerId không được null")
    @Positive(message = "customerId phải là số dương")
    private Long customerId;
}
