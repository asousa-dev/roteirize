package dev.asousa.roteirize.trip;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CreateTripRequestValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        validator = Validation
            .buildDefaultValidatorFactory()
            .getValidator();
    }

    @Test
    void shouldRejectBlankDestination() {
        CreateTripRequest request = new CreateTripRequest(
            " ",
            LocalDate.of(2027, 4, 10),
            LocalDate.of(2027, 4, 18)
        );

        var violations = validator.validate(request);

        assertTrue(
            violations.stream().anyMatch(
                violation -> violation
                    .getMessage()
                    .equals("O destino é obrigatório")
            )
        );
    }

    @Test
    void shouldRejectMissingDates() {
        CreateTripRequest request = new CreateTripRequest(
            "Lisboa",
            null,
            null
        );

        var violations = validator.validate(request);

        assertTrue(
            violations.stream().anyMatch(
                violation -> violation
                    .getMessage()
                    .equals("A data inicial é obrigatória")
            )
        );

        assertTrue(
            violations.stream().anyMatch(
                violation -> violation
                    .getMessage()
                    .equals("A data final é obrigatória")
            )
        );
    }

    @Test
    void shouldRejectEndDateBeforeStartDate() {
        CreateTripRequest request = new CreateTripRequest(
            "Lisboa",
            LocalDate.of(2027, 4, 18),
            LocalDate.of(2027, 4, 10)
        );

        var violations = validator.validate(request);

        assertTrue(
            violations.stream().anyMatch(
                violation -> violation
                    .getMessage()
                    .equals(
                        "A data final deve ser igual ou posterior à data inicial"
                    )
            )
        );
    }

    @Test
    void shouldAcceptValidTrip() {
        CreateTripRequest request = new CreateTripRequest(
            "Lisboa",
            LocalDate.of(2027, 4, 10),
            LocalDate.of(2027, 4, 18)
        );

        var violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }
}