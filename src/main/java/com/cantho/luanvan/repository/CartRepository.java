package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> getCartByCustomerId(long id);

}
