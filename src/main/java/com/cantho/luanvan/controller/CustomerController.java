package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.CustomerDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.service.CustomerService;
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
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<PrimaryResponse<CustomerDTO>> doPost(@RequestBody @Valid CustomerDTO customerDTO){
        return new ResponseEntity<>(PrimaryResponse.<CustomerDTO>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Khách hàng mới được tạo thành công !")
                .timestamp(LocalDateTime.now())
                .data(customerService.createCustomer(customerDTO))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<CustomerDTO>> doGetAll(@RequestParam(name = "page", defaultValue = "0")int page,
                                                              @RequestParam(name = "limit", defaultValue = "10")int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<CustomerDTO> customerDTOS = customerService.getAllCustomer(pageable);
        return new ResponseEntity<>(PageResponse.<CustomerDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Lấy danh sách tất cả khách hàng thành công !")
                .timestamp(LocalDateTime.now())
                .totalElements(customerDTOS.getTotalElements())
                .totalPages(customerDTOS.getTotalPages())
                .currentPage(customerDTOS.getNumber())
                .isLast(customerDTOS.isLast())
                .data(customerDTOS.getContent())
                .build(), HttpStatus.OK);
    }
}
