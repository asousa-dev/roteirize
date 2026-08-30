package dev.asousa.roteirize.location;

public class LocationProviderException
        extends RuntimeException {

    public LocationProviderException(String message) {
        super(message);
    }

    public LocationProviderException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }
}