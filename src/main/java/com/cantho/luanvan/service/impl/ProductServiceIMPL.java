package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.ImageDTO;
import com.cantho.luanvan.dto.request.ProductDTO;
import com.cantho.luanvan.entity.Brand;
import com.cantho.luanvan.entity.Image;
import com.cantho.luanvan.entity.Product;
import com.cantho.luanvan.entity.Promotion;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.ProductMapper;
import com.cantho.luanvan.repository.BrandRepository;
import com.cantho.luanvan.repository.ProductRepository;
import com.cantho.luanvan.repository.PromotionRepository;
import com.cantho.luanvan.service.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceIMPL implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final PromotionRepository promotionRepository;
    private final ProductMapper productMapper;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Brand brand = brandRepository.getBrandById(productDTO.getBrandId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy thương hiệu vơi ID: "+ productDTO.getBrandId())
        );
        Promotion promotion = null;
        if(productDTO.getPromotionId() != null){
             promotion = promotionRepository.getPromotionById(productDTO.getPromotionId()).orElseThrow(
                    () -> new ResourceNotFoundException("Không tìm thấy khuyến mãi vơi ID: "+ productDTO.getPromotionId())
            );
        }

        Product product = Product.builder()
                .name(productDTO.getName())
                .ram(productDTO.getRam())
                .storage(productDTO.getStorage())
//                .stock(0)
                .cpu(productDTO.getCpu())
                .screenSize(productDTO.getScreenSize())
                .gpu(productDTO.getGpu())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .active(false)
                .brand(brand)
                .promotion(promotion)
                .build();

        List<Image> images = new ArrayList<>();
        for (ImageDTO imageDTO : productDTO.getImageDTOList()){
            Image image = Image.builder()
                    .product(product)
                    .imageUrl(imageDTO.getImageUrl())
                    .publicId(imageDTO.getPublicId())
                    .build();
            images.add(image);
        }
        product.setThumbnail(images.get(0).getImageUrl());
        product.setImages(images);

        return productRepository.save(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.getProductById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy sản phẩm với id: "+ id)
        );
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO updateStatusProduct(Long id, boolean active) {
        Product product = productRepository.getProductById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy sản phẩm với id: "+ id)
        );
        product.setActive(active);
        productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.getProductById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy sản phẩm với id: "+ id)
        );
        product.setActive(false);
    }

    @Override
    public Page<ProductDTO> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDTO);
    }

    @Override
    public Page<ProductDTO> getAllProductActive(Pageable papPageable, boolean active) {
        return productRepository.getProductsByActive(active, papPageable).map(productMapper::toDTO);
    }

    @Override
    public Page<ProductDTO> getProductByIdBrand(Pageable pageable, long idBrand) {
        boolean isBrand = brandRepository.existsById(idBrand);
        if(!isBrand)
            throw new ResourceNotFoundException("Không tìm thấy thương hiệu với id: "+ idBrand);

        return productRepository.getProductsByBrand_Id(idBrand, pageable).map(productMapper::toDTO);
    }
}
