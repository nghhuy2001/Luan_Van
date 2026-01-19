package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.ProductDTO;
import com.cantho.luanvan.dto.response.PageResponse;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.service.domain.ProductService;
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
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService ;

    // get all product contain active and non-active
    @GetMapping
    public ResponseEntity<PageResponse<ProductDTO>> doGetAll(@RequestParam(name = "page", defaultValue = "0")   int page,
                                                             @RequestParam(name = "limit", defaultValue = "10")int limit){
        Pageable pageable = PageRequest.of(page, limit) ;
        Page<ProductDTO> result  = productService.getAllProduct(pageable);

        return new ResponseEntity<>(PageResponse.<ProductDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Lấy tát cả sản phẩm thành công !")
                .timestamp(LocalDateTime.now())
                .currentPage(result.getNumber())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isLast(result.isLast())
                .content(result.getContent())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrimaryResponse<ProductDTO>> getByIdProduct(@PathVariable long id){
        return new ResponseEntity<>(PrimaryResponse.<ProductDTO>builder()
                .success(true)
                .status(404)
                .message("Lấy sản phẩm thành công theo id sản phẩm")
                .timestamp(LocalDateTime.now())
                .content(productService.getProductById(id))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/status/{active}")
    public ResponseEntity<PageResponse<ProductDTO>> doGetActive(@RequestParam(name = "page", defaultValue = "0")   int page,
                                                             @RequestParam(name = "limit", defaultValue = "10")int limit,
                                                                @PathVariable boolean active){
        Pageable pageable = PageRequest.of(page, limit) ;
        Page<ProductDTO> result  = productService.getAllProductActive(pageable, active);

        return new ResponseEntity<>(PageResponse.<ProductDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Lấy tát cả sản phẩm theo status thành công !")
                .timestamp(LocalDateTime.now())
                .currentPage(result.getNumber())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isLast(result.isLast())
                .content(result.getContent())
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PrimaryResponse<ProductDTO>> updateStatus(@PathVariable long id,
                                                                    @RequestParam(name = "active") boolean active){
        return new ResponseEntity<>(PrimaryResponse.<ProductDTO>builder()
                .success(true)
                .status(404)
                .message("Cập nhật trạng thái santr phẩm thành công")
                .timestamp(LocalDateTime.now())
                .content(productService.updateStatusProduct(id, active))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PrimaryResponse<ProductDTO>> deleteProduct(@PathVariable long id){
        return new ResponseEntity<>(PrimaryResponse.<ProductDTO>builder()
                .success(true)
                .status(404)
                .message("Xóa mềm santr phẩm thành công")
                .timestamp(LocalDateTime.now())
                .content(productService.updateStatusProduct(id, false))
                .build(), HttpStatus.OK);
    }

}
