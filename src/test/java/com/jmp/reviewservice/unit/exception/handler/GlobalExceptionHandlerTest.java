package com.jmp.reviewservice.unit.exception.handler;

import com.jmp.reviewservice.dto.exception.ErrorResponse;
import com.jmp.reviewservice.exception.CustomException;
import com.jmp.reviewservice.exception.handler.GlobalExceptionHandler;
import com.jmp.reviewservice.mapper.ErrorResponseMapper;
import com.jmp.reviewservice.support.BaseUnitTest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest extends BaseUnitTest {
    @Mock
    private ErrorResponseMapper errorResponseMapper;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    public void dummyMethod(String ignoredDummy) {
        // dummy method
    }

    private MethodParameter createMethodParameter() throws NoSuchMethodException {
        Method method = this.getClass().getDeclaredMethod("dummyMethod", String.class);
        return new MethodParameter(method, 0);
    }

    @Test
    void testHandleCustomException() {
        CustomException customException = new CustomException("Error occurred", "CUSTOM_ERROR") {
        };
        ErrorResponse expectedErrorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                "CUSTOM_ERROR",
                "Error occurred",
                Instant.now()
        );
        when(errorResponseMapper.toErrorResponse(customException)).thenReturn(expectedErrorResponse);

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleCustomException(customException);

        assertEquals(expectedErrorResponse, responseEntity.getBody());
        assertEquals(expectedErrorResponse.status(), responseEntity.getStatusCode());
    }

    @Test
    void testHandleValidationException() throws Exception {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        FieldError fieldError = new FieldError("object", "field", "Field must not be null");
        List<FieldError> fieldErrors = Collections.singletonList(fieldError);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        MethodParameter methodParameter = createMethodParameter();
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);

        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(response.getStatus()).thenReturn(HttpStatus.BAD_REQUEST.value());

        ErrorResponse errorResponse = globalExceptionHandler.handleValidationException(ex, response);

        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.status());
        assertEquals("VALIDATION_ERROR", errorResponse.code());
        assertEquals("Field must not be null", errorResponse.message());
        assertNotNull(errorResponse.timestamp());
    }

    @Test
    void testHandleValidationExceptionFallback() throws Exception {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        List<FieldError> fieldErrors = Collections.emptyList();
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        MethodParameter methodParameter = createMethodParameter();
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);

        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(response.getStatus()).thenReturn(HttpStatus.BAD_REQUEST.value());

        ErrorResponse errorResponse = globalExceptionHandler.handleValidationException(ex, response);

        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.status());
        assertEquals("VALIDATION_ERROR", errorResponse.code());
        assertEquals("Incorrect data in the request", errorResponse.message());
        assertNotNull(errorResponse.timestamp());
    }

    @Test
    void testHandleInternalException() {
        Exception exception = new Exception("Unexpected error");
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(response.getStatus()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());

        ErrorResponse errorResponse = globalExceptionHandler.handleInternalException(exception, response);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse.status());
        assertEquals("INTERNAL_ERROR", errorResponse.code());
        assertEquals("Internal Server Error", errorResponse.message());
        assertNotNull(errorResponse.timestamp());
    }
}
