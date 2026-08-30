package dev.asousa.roteirize.location;

public record LocationSuggestionResponse(
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