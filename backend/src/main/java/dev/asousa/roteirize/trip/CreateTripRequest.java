package dev.asousa.roteirize.trip;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;

public record CreateTripRequest(
    @NotBlank(message = "O destino é obrigatório")
    String destination,

    @NotNull(message = "A data inicial é obrigatória")
    LocalDate startDate,

    @NotNull(message = "A data final é obrigatória")
    LocalDate endDate
) {

    @AssertTrue(
        message = "A data final deve ser igual ou posterior à data inicial"
    )
    public boolean isDateRangeValid() {
        if (startDate == null || endDate == null) {
            return true;
        }

        return !endDate.isBefore(startDate);
    }
}
