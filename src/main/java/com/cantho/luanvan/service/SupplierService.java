package com.cantho.luanvan.service;

import com.cantho.luanvan.dto.request.SupplierDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    SupplierDTO createNewSupplier(SupplierDTO supplierDTO);
    SupplierDTO updateSupplier(Long id,  SupplierDTO supplierDTO);
    void deleteSupplier(Long id);
    SupplierDTO getSupplierById(Long id);
    Page<SupplierDTO> getAllSupplier(Pageable pageable);
    Page<SupplierDTO> searchSupplier(String name, Pageable pageable);
}
