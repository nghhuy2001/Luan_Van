package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.SupplierDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/supplies")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<PrimaryResponse<SupplierDTO>> doPost(@RequestBody @Valid SupplierDTO supplierDTO){
        return new ResponseEntity<>(PrimaryResponse.<SupplierDTO>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Add new supplier successfully !")
                .timestamp(LocalDateTime.now())
                .data(supplierService.createNewSupplier(supplierDTO))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<SupplierDTO>> doGetAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "limit", defaultValue = "10") int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<SupplierDTO> result = supplierService.getAllSupplier(pageable);
        return new ResponseEntity<>(PageResponse.<SupplierDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get all supplier successfully !")
                .timestamp(LocalDateTime.now())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .currentPage(result.getNumber())
                .isLast(result.isLast())
                .data(result.getContent())
                .build(), HttpStatus.OK);
    }
}
