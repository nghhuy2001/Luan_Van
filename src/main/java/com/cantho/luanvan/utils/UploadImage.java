//package com.cantho.luanvan.utils;
//
//import com.cantho.luanvan.entity.Image;
//import com.cantho.luanvan.entity.Product;
//import com.cantho.luanvan.repository.ImageRepository;
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Component
//@RequiredArgsConstructor
//public class UploadImage {
//    private final ImageRepository imageRepository;
//    private final Cloudinary cloudinary;
//
//    public Image uploadImage(MultipartFile file, Product product){
//        try {
//            // Upload lên Cloudinary
//            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
//                    ObjectUtils.asMap("folder", "luan_van"));
//
//            String imageUrl = (String) uploadResult.get("secure_url");
//            String publicId = (String) uploadResult.get("public_id");
//
//            // Luu vao DB
//            Image image = Image.builder()
//                    .product(product)
//                    .imageUrl(imageUrl)
//                    .publicId(publicId)
//                    .build();
//            return imageRepository.save(image);
//        }catch (IOException e){
//            throw new RuntimeException("Upload image failed", e);
//        }
//    }
//}
