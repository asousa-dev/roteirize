package dev.asousa.roteirize.trip;

public record TripDestinationResponse(
        String providerId,
        String displayName,
        String city,
        String state,
        String country,
        String countryCode,
        double latitude,
        double longitude
) {

    public static TripDestinationResponse from(
            TripDestination destination
    ) {
        return new TripDestinationResponse(
                destination.providerId(),
                destination.displayName(),
                destination.city(),
                destination.state(),
                destination.country(),
                destination.countryCode(),
                destination.latitude(),
                destination.longitude()
        );
    }
}