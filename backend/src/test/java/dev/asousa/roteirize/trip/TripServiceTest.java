package dev.asousa.roteirize.trip;

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
}