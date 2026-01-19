package com.cantho.luanvan.controller;

import com.cantho.luanvan.dto.request.CartDTO;
import com.cantho.luanvan.dto.response.PrimaryResponse;
import com.cantho.luanvan.service.domain.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/{idCustomer}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<PrimaryResponse<CartDTO>> doPost(@RequestParam int quantity,
                                                           @RequestParam long productId,
                                                           @PathVariable long idCustomer){
        return new ResponseEntity<>(PrimaryResponse.<CartDTO>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Thêm sản phẩm vào giỏ hàng thành công !")
                .timestamp(LocalDateTime.now())
                .content(cartService.addNewProductIntoCart(productId, quantity, idCustomer))
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PrimaryResponse<CartDTO>> doGetCart(@PathVariable long idCustomer){
        return new ResponseEntity<>(PrimaryResponse.<CartDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Lấy thông tin giỏ hàng thành công !")
                .timestamp(LocalDateTime.now())
                .content(cartService.getCartByIdCustomer(idCustomer))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<PrimaryResponse<CartDTO>> doDelete(@RequestParam long productId,
                                                             @PathVariable long idCustomer){
        return new ResponseEntity<>(PrimaryResponse.<CartDTO>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Xóa sản phẩm khỏi giỏ hàng thành công !")
                .timestamp(LocalDateTime.now())
                .content(cartService.deleteProductInCart(productId, idCustomer))
                .build(),HttpStatus.OK);
    }
}
