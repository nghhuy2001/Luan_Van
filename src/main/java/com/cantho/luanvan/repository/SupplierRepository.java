package com.cantho.luanvan.repository;

import com.cantho.luanvan.dto.request.SupplierDTO;
import com.cantho.luanvan.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsSupplierByEmail(String email);
    boolean existsSupplierByName(String name);
    Optional<Supplier> getSupplierById(Long id);
}
