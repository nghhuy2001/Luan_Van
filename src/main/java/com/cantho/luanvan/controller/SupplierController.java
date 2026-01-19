package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.SupplierDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.service.domain.SupplierService;
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
                .content(supplierService.createNewSupplier(supplierDTO))
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
                .content(result.getContent())
                .build(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrimaryResponse<SupplierDTO>> doUpdate(@PathVariable long id,
                                                                 @RequestBody @Valid SupplierDTO supplierDTO){
        return new ResponseEntity<>(PrimaryResponse.<SupplierDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Update successfully !")
                .timestamp(LocalDateTime.now())
                .content(supplierService.updateSupplier(id, supplierDTO))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> doDelete(@PathVariable long id){
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(PrimaryResponse.builder()
                .success(true)
                .status(HttpStatus.NO_CONTENT.value())
                .message("Delete supplier successfully !")
                .build(), HttpStatus.NO_CONTENT);
    }
}
