package ru.ivanov.ecommerceplatformproject.authservice.exception.exceptionHandling;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ivanov.ecommerceplatformproject.authservice.dto.response.ErrorResponse;
import ru.ivanov.ecommerceplatformproject.authservice.exception.UsernameIsTakenException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ru.ivanov.ecommerceplatformproject.authservice.dto.response.ErrorResponse> handleJwtException(
            JwtException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                ex.getMessage(),
                UNAUTHORIZED.value(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(UsernameIsTakenException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(
            UsernameIsTakenException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                ex.getMessage(),
                CONFLICT.value(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                ex.getMessage(),
                INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }
}
