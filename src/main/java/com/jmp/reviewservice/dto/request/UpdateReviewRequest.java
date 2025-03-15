package com.jmp.reviewservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateReviewRequest {
    @Min(value = 1)
    @Max(value = 5)
    private Integer rating;
    private String advantage;
    private String disadvantage;
    private String comment;
}
