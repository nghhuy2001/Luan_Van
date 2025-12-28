package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.ReviewDTO;
import com.cantho.luanvan.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toEntity(ReviewDTO reviewDTO);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "order.id", target = "orderId")
    ReviewDTO toDTO(Review review);
}
