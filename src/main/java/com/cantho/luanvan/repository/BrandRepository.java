package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsBrandByName(String name);
    Optional<Brand> getBrandById(long id);
}
