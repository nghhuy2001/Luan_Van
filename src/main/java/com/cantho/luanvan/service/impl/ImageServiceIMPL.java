package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.ImageDTO;
import com.cantho.luanvan.entity.Image;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.ImageMapper;
import com.cantho.luanvan.repository.ImageRepository;
import com.cantho.luanvan.service.domain.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceIMPL implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper imageMapper;


    @Override
    public ImageDTO createNewImage(ImageDTO imageDTO) {
        Image image = imageMapper.toEntity(imageDTO);
        Image saved = imageRepository.save(image);
        return imageMapper.toDTO(saved);
    }

    @Override
    public void deleteImageById(Long idImage) {
        boolean existingImg = imageRepository.existsById(idImage);
        if(existingImg)
            throw new ResourceNotFoundException("Not found image with id: "+ idImage);

        imageRepository.deleteById(idImage);
    }

    @Override
    public List<ImageDTO> getAllImageOfProduct(long idImage, long idProduct) {
        return List.of();
    }
}
