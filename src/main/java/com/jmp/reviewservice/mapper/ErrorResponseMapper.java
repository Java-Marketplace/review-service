package com.jmp.reviewservice.mapper;

import com.jmp.reviewservice.dto.exception.ErrorResponse;
import com.jmp.reviewservice.exception.CustomException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Mapper(componentModel = "spring")
public interface ErrorResponseMapper {
    @Mapping(target = "status", expression = "java(getStatusValue(ex))")
    @Mapping(target = "code", expression = "java(ex.getCode())")
    @Mapping(target = "message", expression = "java(ex.getMessage())")
    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
    ErrorResponse toErrorResponse(
            CustomException ex
    );

    default HttpStatus getStatusValue(CustomException ex) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null)
            return responseStatus.value();
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}