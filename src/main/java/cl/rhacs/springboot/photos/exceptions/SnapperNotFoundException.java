package cl.rhacs.springboot.photos.exceptions;

public class SnapperNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param message the detail message
     */
    public SnapperNotFoundException(String message) {
        super(message);
    }

}
