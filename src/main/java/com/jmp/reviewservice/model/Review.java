package com.jmp.reviewservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "review")
@Entity
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private UUID userId;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer rating;

    @Column(length = 2000)
    @Size(min = 5, max = 2000)
    private String advantage;

    @Column(length = 2000)
    @Size(min = 5, max = 2000)
    private String disadvantage;

    @Column(length = 2000)
    @Size(min = 5, max = 2000)
    private String comment;

    @Formula("(SELECT COUNT(*) FROM review_vote rv WHERE rv.review_id = id AND rv.vote_type = 'LIKE')")
    private Long likeCount;

    @Formula("(SELECT COUNT(*) FROM review_vote rv WHERE rv.review_id = id AND rv.vote_type = 'DISLIKE')")
    private Long dislikeCount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
