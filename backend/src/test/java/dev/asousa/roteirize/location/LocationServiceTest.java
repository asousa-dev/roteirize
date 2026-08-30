package dev.asousa.roteirize.location;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.asousa.roteirize.location.LocationSearchProvider;
import dev.asousa.roteirize.location.LocationService;
import dev.asousa.roteirize.location.LocationSuggestionResponse;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationSearchProvider locationSearchProvider;

    @Test
    void shouldReturnCitiesFoundByProvider() {
        LocationSuggestionResponse suggestion =
                new LocationSuggestionResponse(
                        "lisbon-id",
                        "Lisboa, Portugal",
                        "Lisboa",
                        null,
                        "Portugal",
                        "PT",
                        38.7077507,
                        -9.1365919
                );

        given(locationSearchProvider.searchCities("Lis"))
                .willReturn(List.of(suggestion));

        LocationService locationService =
                new LocationService(locationSearchProvider);

        List<LocationSuggestionResponse> result =
                locationService.searchCities("Lis");

        assertThat(result)
                .containsExactly(suggestion);

        verify(locationSearchProvider)
                .searchCities("Lis");
    }
}