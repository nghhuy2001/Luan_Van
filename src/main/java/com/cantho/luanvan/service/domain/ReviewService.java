package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.ReviewDTO;
import com.cantho.luanvan.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Review initReview(ReviewDTO reviewDTO);
    void checkCanReview(Long customerId, Long productId, Long orderId);

    Review save(Review review);

    Page<ReviewDTO> getAllReviewOfProduct(Pageable pageable, Long idProduct);
}
