package dev.asousa.roteirize.common.error;

import java.util.List;

public record ValidationErrorResponse(
    int status,
    String message,
    List<String> errors
) {
}