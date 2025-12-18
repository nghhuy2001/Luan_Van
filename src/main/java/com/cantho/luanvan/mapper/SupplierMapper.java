package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.SupplierDTO;
import com.cantho.luanvan.entity.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toEntity(SupplierDTO supplierDTO);
    SupplierDTO toDTO(Supplier supplier);
}
