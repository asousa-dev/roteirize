package dev.asousa.roteirize.location.geoapify;

import java.util.List;
import java.util.Locale;

import dev.asousa.roteirize.location.LocationSearchProvider;
import dev.asousa.roteirize.location.LocationSuggestionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import dev.asousa.roteirize.location.LocationProviderException;
import org.springframework.web.client.RestClientException;

@Component
public class GeoapifyLocationSearchProvider
        implements LocationSearchProvider {

    private static final int RESULT_LIMIT = 6;

    private final RestClient restClient;
    private final String apiKey;

    public GeoapifyLocationSearchProvider(
            @Value("${integrations.geoapify.base-url}")
            String baseUrl,
            @Value("${integrations.geoapify.api-key}")
            String apiKey
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        this.apiKey = apiKey;
    }

    @Override
    public List<LocationSuggestionResponse> searchCities(
            String query
    ) {
        validateApiKey();

        try {
            GeoapifyAutocompleteResponse response =
                    restClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .path("/autocomplete")
                                    .queryParam("text", query)
                                    .queryParam("type", "city")
                                    .queryParam("format", "json")
                                    .queryParam("limit", RESULT_LIMIT)
                                    .queryParam("lang", "pt")
                                    .queryParam("apiKey", apiKey)
                                    .build())
                            .retrieve()
                            .body(
                                    GeoapifyAutocompleteResponse.class
                            );

            if (response == null
                    || response.results() == null) {
                return List.of();
            }

            return response.results()
                    .stream()
                    .filter(this::hasRequiredData)
                    .map(this::toSuggestion)
                    .toList();
        } catch (RestClientException exception) {
            throw new LocationProviderException(
                    "Falha ao consultar o provedor "
                            + "de localizações.",
                    exception
            );
        }
    }

    private void validateApiKey() {
        if (apiKey == null || apiKey.isBlank()) {
            throw new LocationProviderException(
                    "O provedor de localizações "
                            + "não está configurado."
            );
        }
    }

    private boolean hasRequiredData(
            GeoapifyAutocompleteResponse.Result result
    ) {
        return !isBlank(result.placeId())
                && !isBlank(resolveCity(result))
                && result.lat() != null
                && result.lon() != null;
    }

    private LocationSuggestionResponse toSuggestion(
            GeoapifyAutocompleteResponse.Result result
    ) {
        String city = resolveCity(result);

        return new LocationSuggestionResponse(
                result.placeId(),
                resolveDisplayName(result, city),
                city,
                result.state(),
                result.country(),
                normalizeCountryCode(result.countryCode()),
                result.lat(),
                result.lon()
        );
    }

    private String resolveCity(
            GeoapifyAutocompleteResponse.Result result
    ) {
        if (!isBlank(result.city())) {
            return result.city();
        }

        return result.name();
    }

    private String resolveDisplayName(
            GeoapifyAutocompleteResponse.Result result,
            String city
    ) {
        if (!isBlank(result.formatted())) {
            return result.formatted();
        }

        if (!isBlank(result.country())) {
            return city + ", " + result.country();
        }

        return city;
    }

    private String normalizeCountryCode(String countryCode) {
        if (isBlank(countryCode)) {
            return null;
        }

        return countryCode.toUpperCase(Locale.ROOT);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}