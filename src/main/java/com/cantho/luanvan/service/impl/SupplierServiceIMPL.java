package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.SupplierDTO;
import com.cantho.luanvan.entity.Supplier;
import com.cantho.luanvan.exception.common.DuplicateResourceException;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.SupplierMapper;
import com.cantho.luanvan.repository.SupplierRepository;
import com.cantho.luanvan.service.domain.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceIMPL implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public SupplierDTO createNewSupplier(SupplierDTO supplierDTO) {
        // check trung name va email
        boolean existingName = supplierRepository.existsSupplierByName(supplierDTO.getName());
        if(existingName)
            throw new DuplicateResourceException("Supplier name already exists");
        boolean existingEmail = supplierRepository.existsSupplierByEmail(supplierDTO.getEmail());
        if(existingEmail)
            throw new DuplicateResourceException("Supplier email already exists");

        // handle save
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        supplier.setActive(true);
        Supplier saved = supplierRepository.save(supplier);
        return supplierMapper.toDTO(saved);
    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.getSupplierById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found supplier with id: "+ id)
        );

        supplier.setName(supplierDTO.getName());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setActive(supplierDTO.isActive());

        supplierRepository.save(supplier);

        return supplierMapper.toDTO(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.getSupplierById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found supplier with id: "+ id)
        );
        supplier.setActive(false);
        supplierRepository.save(supplier);
    }

    @Override
    public SupplierDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.getSupplierById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found supplier with id: "+ id)
        );

        return supplierMapper.toDTO(supplier);
    }

    @Override
    public Page<SupplierDTO> getAllSupplier(Pageable pageable) {
        return supplierRepository
                .findAll(pageable)
                .map(supplierMapper::toDTO);
    }

    @Override
    public Page<SupplierDTO> searchSupplier(String name, Pageable pageable) {
        return null;
    }
}
