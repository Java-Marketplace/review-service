package com.jmp.reviewservice.repository;

import com.jmp.reviewservice.model.ReviewMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMessageRepository extends JpaRepository<ReviewMessage, Long> {
}