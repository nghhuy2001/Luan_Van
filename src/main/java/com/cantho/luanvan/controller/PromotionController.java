package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.PromotionDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.entity.Promotion;
import com.cantho.luanvan.service.PromotionService;
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
@RequestMapping("/api/v1/promotions")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<PrimaryResponse<PromotionDTO>> doPost(@RequestBody @Valid PromotionDTO promotionDTO){
        return new ResponseEntity<>(PrimaryResponse.<PromotionDTO>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Add new promotion successfully !")
                .timestamp(LocalDateTime.now())
                .data(promotionService.createNewPromotion(promotionDTO))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<PromotionDTO>> doGetAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                               @RequestParam(name = "limit", defaultValue = "10") int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<PromotionDTO> promotionDTOS = promotionService.getAllPromotion(pageable);
        return new ResponseEntity<>(PageResponse.<PromotionDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get all promotion successfully !")
                .timestamp(LocalDateTime.now())
                .totalPages(promotionDTOS.getTotalPages())
                .totalElements(promotionDTOS.getTotalElements())
                .currentPage(promotionDTOS.getNumber())
                .isLast(promotionDTOS.isLast())
                .data(promotionDTOS.getContent())
                .build(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrimaryResponse<PromotionDTO>> doGetById(@PathVariable Long id){
        return new ResponseEntity<>(PrimaryResponse.<PromotionDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get promotion with id: "+ id + " successfully !")
                .timestamp(LocalDateTime.now())
                .data(promotionService.getPromotionById(id))
                .build(), HttpStatus.OK);
    }
}
