package dev.asousa.roteirize.trip;

import java.util.Locale;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TripDestinationRequest(
        @NotBlank(
                message = "Selecione um destino válido"
        )
        String providerId,

        @NotBlank(
                message = "O nome do destino é obrigatório"
        )
        String displayName,

        @NotBlank(
                message = "A cidade é obrigatória"
        )
        String city,

        String state,

        @NotBlank(
                message = "O país é obrigatório"
        )
        String country,

        @NotBlank(
                message = "O código do país é obrigatório"
        )
        @Pattern(
                regexp = "^[A-Za-z]{2}$",
                message = "O código do país deve ter duas letras"
        )
        String countryCode,

        @NotNull(
                message = "A latitude é obrigatória"
        )
        @DecimalMin(
                value = "-90.0",
                message = "A latitude mínima é -90"
        )
        @DecimalMax(
                value = "90.0",
                message = "A latitude máxima é 90"
        )
        Double latitude,

        @NotNull(
                message = "A longitude é obrigatória"
        )
        @DecimalMin(
                value = "-180.0",
                message = "A longitude mínima é -180"
        )
        @DecimalMax(
                value = "180.0",
                message = "A longitude máxima é 180"
        )
        Double longitude
) {

    public TripDestination toDomain() {
        return new TripDestination(
                providerId,
                displayName,
                city,
                state,
                country,
                countryCode.toUpperCase(Locale.ROOT),
                latitude,
                longitude
        );
    }
}