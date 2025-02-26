package com.jmp.reviewservice.repository;

import com.jmp.reviewservice.model.ReviewVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewVoteRepository extends JpaRepository<ReviewVote, Long> {
}