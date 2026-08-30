package dev.asousa.roteirize.location;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.asousa.roteirize.common.error.ApiExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class LocationControllerErrorTest {

    @Test
    void shouldReturnServiceUnavailableWhenProviderFails()
            throws Exception {

        LocationService locationService =
                mock(LocationService.class);

        given(locationService.searchCities("Lis"))
                .willThrow(
                        new LocationProviderException(
                                "Falha no provedor."
                        )
                );

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(
                        new LocationController(locationService)
                )
                .setControllerAdvice(
                        new ApiExceptionHandler()
                )
                .build();

        mockMvc.perform(
                        get("/api/v1/locations/cities")
                                .param("query", "Lis")
                )
                .andExpect(
                        status().isServiceUnavailable()
                )
                .andExpect(
                        content().contentTypeCompatibleWith(
                                MediaType.APPLICATION_JSON
                        )
                )
                .andExpect(
                        jsonPath("$.status").value(503)
                )
                .andExpect(
                        jsonPath("$.message").value(
                                "O serviço de busca de cidades "
                                        + "está temporariamente "
                                        + "indisponível."
                        )
                )
                .andExpect(
                        jsonPath("$.errors").isArray()
                )
                .andExpect(
                        jsonPath("$.errors").isEmpty()
                );
    }
}