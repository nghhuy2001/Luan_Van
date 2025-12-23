package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.CartDTO;
import com.cantho.luanvan.entity.Cart;
import com.cantho.luanvan.entity.CartItem;
import com.cantho.luanvan.entity.Product;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.CartMapper;
import com.cantho.luanvan.repository.CartRepository;
import com.cantho.luanvan.repository.CustomerRepository;
import com.cantho.luanvan.repository.ProductRepository;
import com.cantho.luanvan.service.domain.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceIMPL implements CartService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper ;


    @Override
    @Transactional
    public CartDTO addNewProductIntoCart(long productId, int quantity, long idCustomer) {
        Product product = productRepository.getProductById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy sản phẩm với ID: "+productId)
        );

        Cart cart = cartRepository.getCartByCustomerId(idCustomer).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy cart của khách hàng với id: " + idCustomer)
        );

        List<CartItem> cartItems = cart.getCartItems();
        if(cartItems != null && existingProductInCart(productId, cartItems)){
            for (CartItem item : cartItems){
                if(item.getProduct().getId().equals(productId)){
                    item.setQuantity(item.getQuantity() + quantity);
                    break;
                }
            }
            cart.setTotalItems(
                    Optional.ofNullable(cart.getTotalItems()).orElse(0) + quantity
            );
            cart.setTotalPrice(cart.getTotalPrice().add(product.getPrice().multiply(BigDecimal.valueOf(quantity))));
            Cart saved = cartRepository.save(cart);
            return cartMapper.toDTO(saved);
        }

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .quantity(quantity)
                .product(product)
                .build();

        cartItems.add(cartItem);

        cart.setTotalItems(
                Optional.of(cart.getTotalItems()).orElse(0) + quantity
        );

        cart.setTotalPrice(cart.getTotalPrice().add(product.getPrice().multiply(BigDecimal.valueOf(quantity))));
        cart.setCartItems(cartItems);
        Cart saved = cartRepository.save(cart);
        return cartMapper.toDTO(saved);
    }

    public boolean existingProductInCart(long idProduct, List<CartItem> cartItems){
        for(CartItem item : cartItems){
            if(item.getProduct().getId() == idProduct) return true;
        }
        return false;
    }

    @Override
    public CartDTO getCartByIdCustomer(long idCustomer) {
        Cart cart = cartRepository.getCartByCustomerId(idCustomer).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy cart của khách hàng với id: " + idCustomer)
        );
        return cartMapper.toDTO(cart);
    }

    @Override
    @Transactional
    public CartDTO deleteProductInCart(long idProduct, long idCustomer) {
        Cart cart = cartRepository.getCartByCustomerId(idCustomer)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy cart của khách hàng với id: " + idCustomer));
        List<CartItem> cartItems = cart.getCartItems();
        CartItem removedItem = null;
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(idProduct)) {
                removedItem = item;
                break;
            }
        }
        if (removedItem == null) {
            throw new ResourceNotFoundException("Sản phẩm không tồn tại trong giỏ hàng");
        }
        // cập nhật tổng
        cart.setTotalItems(
                cart.getTotalItems() - removedItem.getQuantity()
        );
        cart.setTotalPrice(
                cart.getTotalPrice().subtract(
                        removedItem.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(removedItem.getQuantity()))
                )
        );
        // remove trực tiếp
        cartItems.remove(removedItem);
        return cartMapper.toDTO(cartRepository.save(cart));
    }

}
