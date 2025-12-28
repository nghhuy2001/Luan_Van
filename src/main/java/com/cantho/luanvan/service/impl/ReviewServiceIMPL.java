package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.ReviewDTO;
import com.cantho.luanvan.entity.Customer;
import com.cantho.luanvan.entity.Order;
import com.cantho.luanvan.entity.Product;
import com.cantho.luanvan.entity.Review;
import com.cantho.luanvan.exception.common.BusinessException;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.ReviewMapper;
import com.cantho.luanvan.repository.CustomerRepository;
import com.cantho.luanvan.repository.OrderRepository;
import com.cantho.luanvan.repository.ProductRepository;
import com.cantho.luanvan.repository.ReviewRepository;
import com.cantho.luanvan.service.domain.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceIMPL implements ReviewService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Review initReview(ReviewDTO reviewDTO) {
        Product product = productRepository.getProductById(reviewDTO.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy sản phẩm với id: " + reviewDTO.getProductId())
        );
        Customer customer = customerRepository.getCustomerById(reviewDTO.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy khách hàng với id: "+ reviewDTO.getCustomerId())
        );
        Order order = orderRepository.getOrderById(reviewDTO.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy đơn hàng với id: "+ reviewDTO.getOrderId())
        );

        return Review.builder()
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .createdAt(LocalDateTime.now())
                .customer(customer)
                .order(order)
                .product(product)
                .build();
    }

    @Override
    public void checkCanReview(Long customerId, Long productId, Long orderId) {
        Order order = orderRepository.getOrderById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy đơn hàng với id: "+ orderId)
        );
        if (!order.isOwnedById(customerId)) {
            throw new BusinessException("Đơn hàng không thuộc khách hàng");
        }

        if (!order.isCompleted()) {
            throw new BusinessException("Chỉ được review khi đơn hàng đã hoàn thành");
        }

        if (!order.containsProduct(productId)) {
            throw new BusinessException("Sản phẩm không nằm trong đơn hàng");
        }

        if (isReviewed(customerId, orderId, productId)) {
            throw new BusinessException("Sản phẩm đã được đánh giá");
        }
    }

    public boolean isReviewed(Long customerId, Long orderId, Long productId) {
        return reviewRepository
                .existsByProductIdAndOrderIdAndCustomerId(productId, orderId, customerId);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Page<ReviewDTO> getAllReviewOfProduct(Pageable pageable, Long idProduct) {
        return reviewRepository.getReviewsByProductId(pageable, idProduct).map(reviewMapper::toDTO);
    }
}
