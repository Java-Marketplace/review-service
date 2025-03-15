package com.jmp.reviewservice.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private Long productId;
    private UUID userId;
    private Integer rating;
    private String advantage;
    private String disadvantage;
    private String comment;
    private Long likeCount;
    private Long dislikeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
