package com.jmp.reviewservice.mapper;

import com.jmp.reviewservice.dto.exception.ErrorResponse;
import com.jmp.reviewservice.exception.CustomException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ErrorResponseMapper {
    @Mapping(target = "status", expression = "java(ex.getStatus().value())")
    @Mapping(target = "code", expression = "java(ex.getCode())")
    @Mapping(target = "message", expression = "java(ex.getMessage())")
    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
    ErrorResponse toErrorResponse(
            CustomException ex
    );
}