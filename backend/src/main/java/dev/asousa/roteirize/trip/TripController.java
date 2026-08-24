package dev.asousa.roteirize.trip;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<TripResponse> create(
        @Valid @RequestBody CreateTripRequest request
    ) {
        TripResponse response = tripService.create(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping
    public List<TripResponse> findAll(){
        return tripService.findAll();
    }
}
