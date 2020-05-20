package cl.rhacs.springboot.photos.exceptions.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.rhacs.springboot.photos.exceptions.SnapperNotFoundException;
import cl.rhacs.springboot.photos.models.errors.ErrorResponse;

@RestControllerAdvice(basePackages = { "cl.rhacs.springboot.photos" })
public class SnapperExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the {@link SnapperNotFoundException} thrown when the user tries to
     * retrieve the information of an unexisting {@link Snapper}
     *
     * @param exception the thrown {@code UserNotFoundException}
     * @param request   the {@code WebRequest} made by the user
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(value = { SnapperNotFoundException.class })
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFoundException(SnapperNotFoundException exception, WebRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
