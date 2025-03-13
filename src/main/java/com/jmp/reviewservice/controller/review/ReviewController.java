package com.jmp.reviewservice.controller.review;

import com.jmp.reviewservice.dto.review.CreateReviewRequest;
import com.jmp.reviewservice.dto.review.ReviewResponse;
import com.jmp.reviewservice.dto.review.UpdateReviewRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Review Controller", description = "API для управления отзывами о продуктах")
public interface ReviewController {
    @Operation(
        summary = "Получить все отзывы по ID продукта",
        description = "Возвращает список всех отзывов для указанного продукта"
    )
    @ApiResponse(responseCode = "200", description = "Успешное получение отзывов")
    @ApiResponse(responseCode = "404", description = "Продукт не найден")
    List<ReviewResponse> getAllReviewsByProductId(
        @Parameter(description = "ID продукта для поиска отзывов", example = "123")
        @PathVariable Long productId
    );

    @Operation(
        summary = "Создать новый отзыв",
        description = "Создает новый отзыв для продукта"
    )
    @ApiResponse(responseCode = "201", description = "Отзыв успешно создан")
    @ApiResponse(responseCode = "400", description = "Некорректные данные отзыва")
    ReviewResponse createReview(
        @RequestBody(description = "Данные для создания отзыва", required = true)
        @Valid CreateReviewRequest review
    );

    @Operation(
        summary = "Обновить существующий отзыв",
        description = "Обновляет данные отзыва по указанному ID"
    )
    @ApiResponse(responseCode = "200", description = "Отзыв успешно обновлен")
    @ApiResponse(responseCode = "404", description = "Отзыв не найден")
    @ApiResponse(responseCode = "400", description = "Некорректные данные отзыва")
    ReviewResponse updateReview(
        @Parameter(description = "ID обновляемого отзыва", example = "456")
        @PathVariable Long id,
        @RequestBody(description = "Новые данные для отзыва", required = true)
        @Valid UpdateReviewRequest updateReviewRequest
    );

    @Operation(
        summary = "Удалить отзыв по ID",
        description = "Удаляет указанный отзыв из системы"
    )
    @ApiResponse(responseCode = "204", description = "Отзыв успешно удален")
    @ApiResponse(responseCode = "404", description = "Отзыв не найден")
    void deleteReviewById(
        @Parameter(description = "ID удаляемого отзыва", example = "456")
        @PathVariable Long id
    );
}
