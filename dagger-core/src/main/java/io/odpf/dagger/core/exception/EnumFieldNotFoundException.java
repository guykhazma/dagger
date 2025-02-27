package io.odpf.dagger.core.exception;

/**
 * The class Exception if Enum field not found in proto descriptor.
 */
public class EnumFieldNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Enum field not found exception.
     *
     * @param message the message
     */
    public EnumFieldNotFoundException(String message) {
        super(message);
    }
}
