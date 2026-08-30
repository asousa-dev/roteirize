package dev.asousa.roteirize.trip;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public record CreateTripRequest(
        @NotNull(message = "O destino é obrigatório")
        @Valid
        TripDestinationRequest destination,

        @NotNull(message = "A data inicial é obrigatória")
        LocalDate startDate,

        @NotNull(message = "A data final é obrigatória")
        LocalDate endDate
) {

    @AssertTrue(
            message = "A data final deve ser igual "
                    + "ou posterior à data inicial"
    )
    public boolean isDateRangeValid() {
        if (startDate == null || endDate == null) {
            return true;
        }

        return !endDate.isBefore(startDate);
    }
}