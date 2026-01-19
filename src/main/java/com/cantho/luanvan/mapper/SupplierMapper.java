package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.SupplierDTO;
import com.cantho.luanvan.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toEntity(SupplierDTO supplierDTO);

    @Mapping(source = "active", target = "active")
    SupplierDTO toDTO(Supplier supplier);
}
