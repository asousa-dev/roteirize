package dev.asousa.roteirize.common.error;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
        MethodArgumentNotValidException exception
    ) {
        List<String> errors = exception
            .getBindingResult()
            .getAllErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .distinct()
            .toList();

        ValidationErrorResponse response =
            new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos",
                errors
            );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
    }
}