package dev.asousa.roteirize.location;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(
            LocationService locationService
    ) {
        this.locationService = locationService;
    }

    @GetMapping("/cities")
    public List<LocationSuggestionResponse> searchCities(
            @RequestParam
            @NotBlank(message = "A busca deve ser informada.")
            @Size(
                    min = 3,
                    max = 100,
                    message = "A busca deve ter entre 3 e 100 caracteres."
            )
            String query
    ) {
        return locationService.searchCities(query.trim());
    }
}