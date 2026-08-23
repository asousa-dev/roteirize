package dev.asousa.roteirize.trip;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class TripService {
    private final Map<UUID, Trip> trips = new ConcurrentHashMap<>();

    public TripResponse create(CreateTripRequest request){
        UUID id = UUID.randomUUID();

        Trip trip = new Trip(id, request.destination(), request.startDate(), request.endDate());
        trips.put(id, trip);

        return TripResponse.from(trip);
    }
}
