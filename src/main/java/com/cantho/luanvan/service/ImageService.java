package com.cantho.luanvan.service;

import com.cantho.luanvan.dto.request.ImageDTO;

import java.util.List;

public interface ImageService {
    ImageDTO createNewImage(ImageDTO imageDTO);
    void deleteImageById(Long idImage);
    List<ImageDTO> getAllImageOfProduct(long idImage, long idProduct); // thuc hien sau khi co product
}
