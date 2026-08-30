package dev.asousa.roteirize.trip;

public record TripDestination(
        String providerId,
        String displayName,
        String city,
        String state,
        String country,
        String countryCode,
        double latitude,
        double longitude
) {
}