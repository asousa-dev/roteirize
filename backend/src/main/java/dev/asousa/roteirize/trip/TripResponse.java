package dev.asousa.roteirize.trip;

import java.time.LocalDate;
import java.util.UUID;

public record TripResponse(
        UUID id,
        TripDestinationResponse destination,
        LocalDate startDate,
        LocalDate endDate
) {

    public static TripResponse from(Trip trip) {
        return new TripResponse(
                trip.getId(),
                TripDestinationResponse.from(
                        trip.getDestination()
                ),
                trip.getStartDate(),
                trip.getEndDate()
        );
    }
}