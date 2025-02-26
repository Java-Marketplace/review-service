package com.jmp.reviewservice.repository;

import com.jmp.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProductId(Long productId);
}