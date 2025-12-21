package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.ProductDTO;
import com.cantho.luanvan.entity.Product;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);

    Page<ProductDTO> getAllProduct(Pageable pageable);
    Page<ProductDTO> getAllProductActive(Pageable papPageable);
    Page<ProductDTO> getProductByIdBrand(Pageable pageable, long idBrand);
}
