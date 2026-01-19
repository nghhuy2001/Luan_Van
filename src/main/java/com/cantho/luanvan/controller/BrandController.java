package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.BrandDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.service.domain.BrandService;
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
@RequestMapping("/api/v1/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<PrimaryResponse<BrandDTO>> doPost(@RequestBody @Valid BrandDTO brandDTO){
        return new ResponseEntity<>(PrimaryResponse.<BrandDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Add new brand successfully !")
                .timestamp(LocalDateTime.now())
                .content(brandService.createNewBrand(brandDTO))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<BrandDTO>> doGetAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "limit", defaultValue = "10")int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<BrandDTO> result = brandService.getAllBrand(pageable);
        return new ResponseEntity<>(PageResponse.<BrandDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get all brand successfully !")
                .timestamp(LocalDateTime.now())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .currentPage(result.getNumber())
                .isLast(result.isLast())
                .content(result.getContent())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrimaryResponse<BrandDTO>> doGetById(@PathVariable long id){
        return new ResponseEntity<>(PrimaryResponse.<BrandDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get brand with id: "+ id +" successfully !")
                .timestamp(LocalDateTime.now())
                .content(brandService.getBrandById(id))
                .build(), HttpStatus.OK);
    }
}
