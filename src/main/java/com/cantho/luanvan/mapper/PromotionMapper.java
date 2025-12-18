package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.PromotionDTO;
import com.cantho.luanvan.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    Promotion toEntity(PromotionDTO promotionDTO);
    PromotionDTO toDTO(Promotion promotion);
}
