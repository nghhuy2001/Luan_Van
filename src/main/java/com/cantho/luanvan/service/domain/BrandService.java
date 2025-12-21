package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {
    BrandDTO createNewBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(Long id, BrandDTO brandDTO);
    void deleteBrand(Long id);
    BrandDTO getBrandById(Long id);
    Page<BrandDTO> getAllBrand(Pageable pageable);
    Page<BrandDTO> searchBrand(String name, Pageable pageable);
}
