package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getProductById(Long id);
    Page<Product> getProductsByActive(boolean active, Pageable pageable);

    Page<Product> getProductsByBrand_Id(Long brandId, Pageable pageable);

}
