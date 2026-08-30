package dev.asousa.roteirize.common.error;

import java.util.List;

public record ApiErrorResponse(
        int status,
        String message,
        List<String> errors
) {

    public ApiErrorResponse(
            int status,
            String message
    ) {
        this(status, message, List.of());
    }
}