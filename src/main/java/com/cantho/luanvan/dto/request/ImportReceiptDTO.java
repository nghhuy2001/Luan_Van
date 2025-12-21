package com.cantho.luanvan.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportReceiptDTO {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime importDate;
    private long employeeId;
    private long supplierId;
    private List<ImportReceiptItemDTO> importReceiptItemDTOS = new ArrayList<>();
}
