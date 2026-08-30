package dev.asousa.roteirize.location;

import java.util.List;

public interface LocationSearchProvider {

    List<LocationSuggestionResponse> searchCities(
            String query
    );
}