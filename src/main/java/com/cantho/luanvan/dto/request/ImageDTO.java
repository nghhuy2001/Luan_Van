package com.cantho.luanvan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long id;
    private String imageUrl;
    private String publicId; // id cua anh  Cloudinary
}
