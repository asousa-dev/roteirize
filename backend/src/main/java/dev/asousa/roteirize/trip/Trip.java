package dev.asousa.roteirize.trip;

import java.time.LocalDate;
import java.util.UUID;

public class Trip{
    private final UUID id;
    private final String destination;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Trip(
        UUID id,
        String destination,
        LocalDate startDate,
        LocalDate endDate
    ) {
        this.id = id;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getId(){
        return id;
    }

    public String getDestination(){
        return destination;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }




}