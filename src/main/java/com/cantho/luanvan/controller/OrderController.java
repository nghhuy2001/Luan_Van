package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.OrderDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.service.application.CreateOrderApplicationService;
import com.cantho.luanvan.service.domain.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final CreateOrderApplicationService createOrderApplicationService;
    private  final OrderService orderService;

    @PostMapping
    public ResponseEntity<PrimaryResponse<?>> doPost(@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<>(PrimaryResponse.builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Tạo đơn hàng thành công !")
                .timestamp(LocalDateTime.now())
                .data(createOrderApplicationService.createOrder(orderDTO))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<OrderDTO>> doGetForAdmin(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                @RequestParam(name = "limit", defaultValue = "10") int limit){
        Pageable pageable = PageRequest.of(page, limit) ;
        Page<OrderDTO> result = orderService.getOrderAllOrder(pageable);
        return new ResponseEntity<>(PageResponse.<OrderDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Admin: Lấy tất cả đơn hàng thành công !")
                .timestamp(LocalDateTime.now())
                .isLast(result.isLast())
                .currentPage(result.getNumber())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .data(result.getContent())
                .build(), HttpStatus.OK);
    }
}
