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
    void shouldRejectMissingDestination() {
        CreateTripRequest request =
                new CreateTripRequest(
                        null,
                        LocalDate.of(2027, 4, 10),
                        LocalDate.of(2027, 4, 18)
                );

        var violations = validator.validate(request);

        assertTrue(
                violations.stream().anyMatch(
                        violation -> violation
                                .getMessage()
                                .equals(
                                        "O destino é obrigatório"
                                )
                )
        );
    }

    @Test
    void shouldRejectDestinationWithoutProviderId() {
        TripDestinationRequest destination =
                new TripDestinationRequest(
                        " ",
                        "Lisboa, Portugal",
                        "Lisboa",
                        null,
                        "Portugal",
                        "PT",
                        38.7077507,
                        -9.1365919
                );

        CreateTripRequest request =
                new CreateTripRequest(
                        destination,
                        LocalDate.of(2027, 4, 10),
                        LocalDate.of(2027, 4, 18)
                );

        var violations = validator.validate(request);

        assertTrue(
                violations.stream().anyMatch(
                        violation -> violation
                                .getMessage()
                                .equals(
                                        "Selecione um destino válido"
                                )
                )
        );
    }

    @Test
    void shouldRejectMissingDates() {
        CreateTripRequest request =
                new CreateTripRequest(
                        validDestination(),
                        null,
                        null
                );

        var violations = validator.validate(request);

        assertTrue(
                violations.stream().anyMatch(
                        violation -> violation
                                .getMessage()
                                .equals(
                                        "A data inicial é obrigatória"
                                )
                )
        );

        assertTrue(
                violations.stream().anyMatch(
                        violation -> violation
                                .getMessage()
                                .equals(
                                        "A data final é obrigatória"
                                )
                )
        );
    }

    @Test
    void shouldRejectEndDateBeforeStartDate() {
        CreateTripRequest request =
                new CreateTripRequest(
                        validDestination(),
                        LocalDate.of(2027, 4, 18),
                        LocalDate.of(2027, 4, 10)
                );

        var violations = validator.validate(request);

        assertTrue(
                violations.stream().anyMatch(
                        violation -> violation
                                .getMessage()
                                .equals(
                                        "A data final deve ser igual "
                                                + "ou posterior à "
                                                + "data inicial"
                                )
                )
        );
    }

    @Test
    void shouldAcceptValidTrip() {
        CreateTripRequest request =
                new CreateTripRequest(
                        validDestination(),
                        LocalDate.of(2027, 4, 10),
                        LocalDate.of(2027, 4, 18)
                );

        var violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    private static TripDestinationRequest
            validDestination() {

        return new TripDestinationRequest(
                "lisbon-id",
                "Lisboa, Portugal",
                "Lisboa",
                null,
                "Portugal",
                "PT",
                38.7077507,
                -9.1365919
        );
    }
}