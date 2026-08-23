package dev.asousa.roteirize.trip;

import java.time.LocalDate;

public record CreateTripRequest(
    String destination,
    LocalDate startDate,
    LocalDate endDate
) {
}
