package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.CartDTO;

public interface CartService {
    CartDTO addNewProductIntoCart(long productId, int quantity, long idCustomer);
    CartDTO getCartByIdCustomer(long idCustomer);
    CartDTO deleteProductInCart(long idProduct, long idCustomer); // id -> id cua product trong cart != id cua product
}
