package com.jmp.reviewservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    private Long userId;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer rating;

    @Column(length = 2000)
    @Size(min = 5, max = 2000)
    private String advantage;

    @Column(length = 2000)
    @Size(min = 5, max = 2000)
    private String disadvantages;

    @Column(length = 2000)
    @Size(min = 5, max = 2000)
    private String comment;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewMessage> answers;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewVote> votes;

    @Transient
    private Long likeCount;

    @Transient
    private Long dislikeCount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Long getLikeCount() {
        return votes.stream()
                .filter(v -> v.getVoteType() == VoteType.LIKE)
                .count();
    }

    public Long getDislikeCount() {
        return votes.stream()
                .filter(v -> v.getVoteType() == VoteType.DISLIKE)
                .count();
    }
}
