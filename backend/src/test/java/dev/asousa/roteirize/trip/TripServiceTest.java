package dev.asousa.roteirize.trip;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

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
        CreateTripRequest request = new CreateTripRequest(
            "Rio de Janeiro",
            LocalDate.of(2026, 12, 10),
            LocalDate.of(2026, 12, 15)
        );

        TripResponse response = tripService.create(request);

        assertNotNull(response.id());
        assertEquals(
            "Rio de Janeiro",
            response.destination()
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
    List<TripResponse> trips = tripService.findAll();

    assertTrue(trips.isEmpty());
}

@Test
void shouldListCreatedTrips() {
    tripService.create(
        new CreateTripRequest(
            "Lisboa",
            LocalDate.of(2027, 4, 10),
            LocalDate.of(2027, 4, 18)
        )
    );

    tripService.create(
        new CreateTripRequest(
            "Paris",
            LocalDate.of(2027, 4, 19),
            LocalDate.of(2027, 4, 25)
        )
    );

    List<TripResponse> trips = tripService.findAll();

    List<String> destinations = trips
        .stream()
        .map(TripResponse::destination)
        .toList();

    assertEquals(2, trips.size());
    assertTrue(destinations.contains("Lisboa"));
    assertTrue(destinations.contains("Paris"));
}
    
}