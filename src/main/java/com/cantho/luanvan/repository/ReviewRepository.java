package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByProductIdAndOrderIdAndCustomerId(
            Long productId,
            Long orderId,
            Long customerId
    );

    Page<Review> getReviewsByProductId(Pageable pageable, long id);
}
