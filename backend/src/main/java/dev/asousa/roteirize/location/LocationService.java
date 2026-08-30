package dev.asousa.roteirize.location;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationSearchProvider locationSearchProvider;

    public LocationService(
            LocationSearchProvider locationSearchProvider
    ) {
        this.locationSearchProvider =
                locationSearchProvider;
    }

    public List<LocationSuggestionResponse> searchCities(
            String query
    ) {
        return locationSearchProvider.searchCities(query);
    }
}