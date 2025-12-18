package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.ImageDTO;
import com.cantho.luanvan.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toEntity(ImageDTO imageDTO);
    ImageDTO toDTO(Image image);
}
