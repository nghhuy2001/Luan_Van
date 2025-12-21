package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.CustomerAddressDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.service.domain.CustomerAddressService;
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
@RequestMapping("/api/v1/customer/address/{idCustomer}")
public class CustomerAddressController {
    @Autowired
    private CustomerAddressService customerAddressService;

    @PostMapping
    private ResponseEntity<PrimaryResponse<CustomerAddressDTO>> doPost(@PathVariable long idCustomer,
                                                                       @RequestBody @Valid CustomerAddressDTO customerAddressDTO){
        return new ResponseEntity<>(PrimaryResponse.<CustomerAddressDTO>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Thêm địa chỉ thành công !!")
                .timestamp(LocalDateTime.now())
                .data(customerAddressService.createCustomerAddressWithIdCustomer(idCustomer, customerAddressDTO))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<PageResponse<CustomerAddressDTO>> doGetAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                      @RequestParam(name = "limit", defaultValue = "10")int limit,
                                                                      @PathVariable long idCustomer){
        Pageable pageable = PageRequest.of(page, limit);
        Page<CustomerAddressDTO> result = customerAddressService.getAllCustomerAddress(pageable, idCustomer);

        return new ResponseEntity<>(PageResponse.<CustomerAddressDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Lấy danh sách địa chỉ của khách hàng thành công !")
                .timestamp(LocalDateTime.now())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .currentPage(result.getNumber())
                .isLast(result.isLast())
                .data(result.getContent())
                .build(),HttpStatus.OK);
    }

}
