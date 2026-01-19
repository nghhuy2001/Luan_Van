package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.ReviewDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.entity.Review;
import com.cantho.luanvan.service.application.ReviewOrderApplicationService;
import com.cantho.luanvan.service.domain.ReviewService;
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
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewOrderApplicationService reviewOrderApplicationService;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<PrimaryResponse<ReviewDTO>> doPost(@RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(PrimaryResponse.<ReviewDTO>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Đánh giá sản phẩm thành công !")
                .timestamp(LocalDateTime.now())
                .content(reviewOrderApplicationService.doHandle(reviewDTO))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/product/{idProduct}")
    public ResponseEntity<PageResponse<ReviewDTO>> doGetAll(@PathVariable long idProduct,
                                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                                            @RequestParam(name = "limit", defaultValue = "10") int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<ReviewDTO> result = reviewService.getAllReviewOfProduct(pageable, idProduct);

        return new ResponseEntity<>(PageResponse.<ReviewDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Lấy đánh giá của sản phẩm thành công !")
                .timestamp(LocalDateTime.now())
                .currentPage(result.getNumber())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .content(result.getContent())
                .build(), HttpStatus.OK);
    }
}
