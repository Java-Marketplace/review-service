package com.jmp.reviewservice.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateReviewRequest {
    @NotNull
    private Long productId;
    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    private Integer rating;
    private String advantage;
    private String disadvantage;
    private String comment;
}
