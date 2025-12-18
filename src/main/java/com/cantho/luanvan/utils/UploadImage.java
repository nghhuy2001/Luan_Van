package com.cantho.luanvan.utils;

import com.cantho.luanvan.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploadImage {
    private final ImageRepository imageRepository;
//    private final Cloudinary cloudinary;
//
//    public Image uploadImage(MultipartFile file, Step step){
//        try {
//            // Upload lên Cloudinary
//            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
//                    ObjectUtils.asMap("folder", "recipes"));
//
//            String imageUrl = (String) uploadResult.get("secure_url");
//            String publicId = (String) uploadResult.get("public_id");
//
//            // Luu vao DB
//            Image image = Image.builder()
//                    .step(step)
//                    .imageUrl(imageUrl)
//                    .public_id(publicId)
//                    .build();
//            return imageRepository.save(image);
//        }catch (IOException e){
//            throw new RuntimeException("Upload image failed", e);
//        }
//    }
}
