package com.prokarma.producer.exceptions;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.prokarma.producer.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setUserMessage("Validation Error.");
        errorResponse.setSystemMessage(errors);
        errorResponse.setDetailLink(request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PublisherServiceException.class)
    public final ResponseEntity<ErrorResponse> handleException(PublisherServiceException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(String.valueOf(ex.getStatusCode()));
        errorResponse.setSystemMessage(ex.getMessage());
        errorResponse.setUserMessage(ex.getLocalizedMessage());
        errorResponse.setDetailLink(request.getRequestURL().toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
