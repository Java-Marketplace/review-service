package com.jmp.reviewservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min = 5, max = 2000)
    private String advantage;

    @Size(min = 5, max = 2000)
    private String disadvantage;

    @Size(min = 5, max = 2000)
    private String comment;
}
