package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.PromotionDTO;
import com.cantho.luanvan.entity.Promotion;
import com.cantho.luanvan.exception.common.DuplicateResourceException;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.PromotionMapper;
import com.cantho.luanvan.repository.PromotionRepository;
import com.cantho.luanvan.service.domain.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceIMPL implements PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionMapper promotionMapper;


    @Override
    public PromotionDTO createNewPromotion(PromotionDTO promotionDTO) {
        // check trung name
        boolean existsName = promotionRepository.existsByName(promotionDTO.getName());
        if (existsName)
            throw new DuplicateResourceException("Name promotion is existing !");

        // check nhap sai ngay
        if (promotionDTO.getEndDate().isBefore(promotionDTO.getStartDate())) {
            throw new IllegalArgumentException("Ngày kết thúc phải sau ngày bắt đầu");
        }

        Promotion promotion = promotionMapper.toEntity(promotionDTO);
        Promotion saved = promotionRepository.save(promotion);
        return promotionMapper.toDTO(saved);
    }

    @Override
    public PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO) {
        return null;
    }

    @Override
    public void deletePromotion(Long id) {

    }

    @Override
    public PromotionDTO getPromotionById(Long id) {
        Promotion promotion = promotionRepository.getPromotionById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found promotion with id: "+ id)
        );
        return promotionMapper.toDTO(promotion);
    }

    @Override
    public Page<PromotionDTO> getAllPromotion(Pageable pageable) {
        return promotionRepository.findAll(pageable).map(promotionMapper::toDTO);
    }

    @Override
    public Page<PromotionDTO> searchPromotion(String name, Pageable pageable) {
        return null;
    }
}
