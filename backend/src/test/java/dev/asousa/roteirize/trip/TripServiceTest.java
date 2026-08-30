package dev.asousa.roteirize.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripServiceTest {

    private TripService tripService;

    @BeforeEach
    void setUp() {
        tripService = new TripService();
    }

    @Test
    void shouldCreateTripWithGeneratedId() {
        TripDestinationRequest destination =
                destination(
                        "rio-id",
                        "Rio de Janeiro, Brasil",
                        "Rio de Janeiro",
                        "Brasil",
                        "BR",
                        -22.9068,
                        -43.1729
                );

        CreateTripRequest request =
                new CreateTripRequest(
                        destination,
                        LocalDate.of(2026, 12, 10),
                        LocalDate.of(2026, 12, 15)
                );

        TripResponse response =
                tripService.create(request);

        assertNotNull(response.id());

        assertEquals(
                "rio-id",
                response.destination().providerId()
        );

        assertEquals(
                "Rio de Janeiro",
                response.destination().city()
        );

        assertEquals(
                "BR",
                response.destination().countryCode()
        );

        assertEquals(
                LocalDate.of(2026, 12, 10),
                response.startDate()
        );

        assertEquals(
                LocalDate.of(2026, 12, 15),
                response.endDate()
        );
    }

    @Test
    void shouldReturnEmptyListWhenNoTripsExist() {
        List<TripResponse> trips =
                tripService.findAll();

        assertTrue(trips.isEmpty());
    }

    @Test
    void shouldListCreatedTrips() {
        tripService.create(
                new CreateTripRequest(
                        destination(
                                "lisbon-id",
                                "Lisboa, Portugal",
                                "Lisboa",
                                "Portugal",
                                "PT",
                                38.7077507,
                                -9.1365919
                        ),
                        LocalDate.of(2027, 4, 10),
                        LocalDate.of(2027, 4, 18)
                )
        );

        tripService.create(
                new CreateTripRequest(
                        destination(
                                "paris-id",
                                "Paris, França",
                                "Paris",
                                "França",
                                "FR",
                                48.8566,
                                2.3522
                        ),
                        LocalDate.of(2027, 4, 19),
                        LocalDate.of(2027, 4, 25)
                )
        );

        List<TripResponse> trips =
                tripService.findAll();

        List<String> destinations = trips
                .stream()
                .map(
                        trip -> trip
                                .destination()
                                .city()
                )
                .toList();

        assertEquals(2, trips.size());
        assertTrue(destinations.contains("Lisboa"));
        assertTrue(destinations.contains("Paris"));
    }

    private TripDestinationRequest destination(
            String providerId,
            String displayName,
            String city,
            String country,
            String countryCode,
            double latitude,
            double longitude
    ) {
        return new TripDestinationRequest(
                providerId,
                displayName,
                city,
                null,
                country,
                countryCode,
                latitude,
                longitude
        );
    }
}