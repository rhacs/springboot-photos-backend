package cl.rhacs.springboot.photos.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.rhacs.springboot.photos.exceptions.PhotoNotFoundException;
import cl.rhacs.springboot.photos.models.errors.ErrorResponse;

@RestControllerAdvice(basePackages = { "cl.rhacs.springboot.photos" })
public class PhotoExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the {@link PhotoNotFoundException} when the photo details requested
     * by the user does not exists
     *
     * @param exception the thrown {@code PhotoNotFoundException}
     * @param request   the {@code WebRequest} made by the user
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(value = { PhotoNotFoundException.class })
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlePhotoNotFoundException(PhotoNotFoundException exception,
            WebRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
