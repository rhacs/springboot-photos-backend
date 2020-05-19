package cl.rhacs.springboot.photos.exceptions;

public class PhotoNotFoundException extends RuntimeException {

    // Constants
    // -----------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new {@link PhotoNotFoundException} with {@code null} as its detail
     * message
     */
    public PhotoNotFoundException() {
        super();
    }

    /**
     * Creates a new {@link PhotoNotFoundException} with the specified detail
     * message
     *
     * @param message the detail message
     */
    public PhotoNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a new {@link PhotoNotFoundException} with the specified cause and the
     * detail message of {@code (cause == null ? null : cause.toString())}
     *
     * @param cause the cause
     */
    public PhotoNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new {@link PhotoNotFoundException} with the specified cause and the
     * specified detail message
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public PhotoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
