package com.cantho.luanvan.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO {

    private Long id;

    @NotBlank(message = "Tên khuyến mãi không được để trống")
    @Size(min = 3, max = 100, message = "Tên khuyến mãi phải từ 3 đến 100 ký tự")
    private String name;

    @Min(value = 1, message = "Giá trị khuyến mãi phải >= 1")
    @Max(value = 100, message = "Giá trị khuyến mãi không được vượt quá 100")
    private int value;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
}
