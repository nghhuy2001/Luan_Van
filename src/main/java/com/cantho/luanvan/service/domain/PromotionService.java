package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.PromotionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {
    PromotionDTO createNewPromotion(PromotionDTO promotionDTO);
    PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO);
    void deletePromotion(Long id);
    PromotionDTO getPromotionById(Long id);
    Page<PromotionDTO> getAllPromotion(Pageable pageable);
    Page<PromotionDTO> searchPromotion(String name, Pageable pageable);
}
