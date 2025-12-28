package com.cantho.luanvan.service.application;

import com.cantho.luanvan.dto.request.ReviewDTO;
import com.cantho.luanvan.entity.Review;
import com.cantho.luanvan.mapper.ReviewMapper;
import com.cantho.luanvan.service.domain.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewOrderApplicationService {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewDTO doHandle(ReviewDTO reviewDTO){
        reviewService.checkCanReview(reviewDTO.getCustomerId(), reviewDTO.getProductId(), reviewDTO.getOrderId());

        Review review = reviewService.initReview(reviewDTO);

        Review saved = reviewService.save(review);

        return reviewMapper.toDTO(saved);
    }


}
