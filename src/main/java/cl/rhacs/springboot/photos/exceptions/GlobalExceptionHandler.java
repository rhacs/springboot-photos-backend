package cl.rhacs.springboot.photos.exceptions;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.rhacs.springboot.photos.models.errors.ErrorResponse;

@RestControllerAdvice(basePackages = { "cl.rhacs.springboot.photos" })
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the {@link PhotoNotFoundException} when the photo details requested
     * by the user does not exists
     *
     * @param exception PhotoNotFoundException
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { PhotoNotFoundException.class })
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlePhotoNotFoundException(final PhotoNotFoundException exception,
            final WebRequest request) {
        final ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the {@link ContentNotFoundException} when the repository is empty
     *
     * @param exception ContentNotFoundException
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { ContentNotFoundException.class })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<ErrorResponse> handleContentNotFoundException(final ContentNotFoundException exception,
            final WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NO_CONTENT, exception), HttpStatus.NO_CONTENT);
    }

    /**
     * Handles the {@link IndexOutOfBoundsException}. Thrown to indicate that an
     * index of some sort (such as to an array, to a string, or to a vector) is out
     * of range.
     *
     * @param exception IndexOutOfBoundsException
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { IndexOutOfBoundsException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIndexOutOfBoundsException(final IndexOutOfBoundsException exception,
            final WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link IllegalArgumentException}. Thrown to indicate that a
     * method has been passed an illegal or inappropriate argument.
     *
     * @param exception IllegalArgumentException
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException exception,
            final WebRequest request) {
        final ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, exception);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link PropertyReferenceException}. Exception being thrown when
     * creating PropertyPath instances.
     *
     * @param exception PropertyReferenceException
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { PropertyReferenceException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlePropertyReferenceException(final PropertyReferenceException exception,
            final WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception), new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    // Inheritances (ResponseEntityExceptionHandler)
    // -----------------------------------------------------------------------------------------

    /**
     * Handles the {@link NoHandlerFoundException} when the user tries to access a
     * non defined context path
     *
     * @param exception NoHandlerFoundException
     * @param headers   HttpHeaders
     * @param status    HttpStatus
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @Override
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException exception,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST);
        response.setMessage(String.format("Could not find the method %s for the URL %s", exception.getHttpMethod(),
                exception.getRequestURL()));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
