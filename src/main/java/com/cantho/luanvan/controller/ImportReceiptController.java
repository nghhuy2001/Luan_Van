package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.ImportReceiptDTO;
import com.cantho.luanvan.dto.request.ProductDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.entity.ImportReceipt;
import com.cantho.luanvan.service.application.ImportReceiptApplicationService;
import com.cantho.luanvan.service.domain.ImportReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/import-receipts")
@RequiredArgsConstructor
public class ImportReceiptController {
    private final ImportReceiptApplicationService importReceiptApplicationService;
    private final ImportReceiptService importReceiptService;

    @PostMapping
    public ResponseEntity<PrimaryResponse<?>> doPost(@RequestBody ImportReceiptDTO importReceiptDTO){
        return new ResponseEntity<>(PrimaryResponse.builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Tạo phiếu nhập thành công !")
                .timestamp(LocalDateTime.now())
                .content(importReceiptApplicationService.createImportReceipt(importReceiptDTO))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<ImportReceiptDTO>> doGetAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                   @RequestParam(name = "limit", defaultValue = "10")int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<ImportReceiptDTO> result =  importReceiptService.getAllReceipt(pageable);
        return new ResponseEntity<>(PageResponse.<ImportReceiptDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Lấy tất cả phiếu nhập hàng thành công !")
                .timestamp(LocalDateTime.now())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .currentPage(result.getNumber())
                .isLast(result.isLast())
                .content(result.getContent())
                .build(),HttpStatus.OK);
    }
}
