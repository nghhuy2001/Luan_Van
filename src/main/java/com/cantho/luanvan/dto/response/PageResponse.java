package com.cantho.luanvan.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private boolean success;
    private int status;
    private String message;
    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private long totalPages;
    private long totalElements;
    private int currentPage;
    private boolean isLast;
    private List<T> data;
}
