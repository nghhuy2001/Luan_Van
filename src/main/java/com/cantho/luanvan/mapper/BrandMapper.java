package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.BrandDTO;
import com.cantho.luanvan.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toEntity(BrandDTO brandDTO);
    BrandDTO toDTO(Brand brand);
    void updateEntityFromDTO(BrandDTO brandDTO, @MappingTarget Brand brand);
}
