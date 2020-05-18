package cl.rhacs.springboot.photos.exceptions;

public class ContentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8333813258438303148L;

    /**
     * Constructs a new runtime exception with {@code null} as its detail message.
     */
    public ContentNotFoundException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param message the detail message
     */
    public ContentNotFoundException(final String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())}
     *
     * @param cause the cause
     */
    public ContentNotFoundException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public ContentNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
