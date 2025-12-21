package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.BrandDTO;
import com.cantho.luanvan.entity.Brand;
import com.cantho.luanvan.exception.common.DuplicateResourceException;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.BrandMapper;
import com.cantho.luanvan.repository.BrandRepository;
import com.cantho.luanvan.service.domain.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceIMPL implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BrandDTO createNewBrand(BrandDTO brandDTO) {
        boolean existsBrand = brandRepository.existsBrandByName(brandDTO.getName());
        if(existsBrand){
            throw  new DuplicateResourceException("Name brand is existing !");
        }
        Brand brand = brandMapper.toEntity(brandDTO);
        brand.setActive(true);
        Brand saved = brandRepository.save(brand);
        return brandMapper.toDTO(saved);
    }

    @Override
    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        return null;
    }

    @Override
    public void deleteBrand(Long id) {

    }

    @Override
    public BrandDTO getBrandById(Long id) {
        boolean existsBrand = brandRepository.getBrandById(id).isPresent();
        if(!existsBrand) throw new ResourceNotFoundException("Not found brand with id: "+ id);
        Optional<Brand> brand = brandRepository.getBrandById(id);
        return brandMapper.toDTO(brand.get());
    }

    @Override
    public Page<BrandDTO> getAllBrand(Pageable pageable) {
        return brandRepository
                .findAll(pageable)
                .map(brandMapper::toDTO);
    }

    @Override
    public Page<BrandDTO> searchBrand(String name, Pageable pageable) {
        return null;
    }
}
