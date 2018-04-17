package fr.berger.qube.exceptions;

import fr.berger.qube.Scale;

public class ScaleNotValidException extends RuntimeException {

    public ScaleNotValidException(double scale) {
        super("The scale \"" + scale + "\" is not valid (required to be a power of ten, like 0.001, 10, 1'000, ...)");
    }
    public ScaleNotValidException() {
        super("The scale is not valid (required to be a power of ten, like 0.001, 10, 1'000, ...)");
    }
    public ScaleNotValidException(String message) {
        super(message);
    }
    public ScaleNotValidException(String message, Exception innerException) {
        super(message, innerException);
    }
}
