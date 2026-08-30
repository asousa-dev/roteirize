package dev.asousa.roteirize.location.geoapify;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoapifyAutocompleteResponse(
        List<Result> results
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Result(
            @JsonProperty("place_id")
            String placeId,

            String name,
            String formatted,
            String city,
            String state,
            String country,

            @JsonProperty("country_code")
            String countryCode,

            Double lat,
            Double lon
    ) {
    }
}