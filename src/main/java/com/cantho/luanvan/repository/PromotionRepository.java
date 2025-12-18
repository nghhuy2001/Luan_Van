package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> getPromotionById(Long id);
    boolean existsByName(String name);
}
