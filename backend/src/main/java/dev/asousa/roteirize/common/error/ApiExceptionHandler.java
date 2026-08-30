package dev.asousa.roteirize.common.error;

import dev.asousa.roteirize.location.LocationProviderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(LocationProviderException.class)
    public ResponseEntity<ApiErrorResponse>
            handleLocationProviderException() {

        HttpStatus status =
                HttpStatus.SERVICE_UNAVAILABLE;

        ApiErrorResponse response =
                new ApiErrorResponse(
                        status.value(),
                        "O serviço de busca de cidades "
                                + "está temporariamente indisponível."
                );

        return ResponseEntity
                .status(status)
                .body(response);
    }
}