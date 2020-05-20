package cl.rhacs.springboot.photos.exceptions.handlers;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.rhacs.springboot.photos.exceptions.ContentNotFoundException;
import cl.rhacs.springboot.photos.models.errors.ErrorResponse;

@RestControllerAdvice(basePackages = { "cl.rhacs.springboot.photos" })
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the {@link ContentNotFoundException} when the repository is empty
     *
     * @param exception ContentNotFoundException
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { ContentNotFoundException.class })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    protected ResponseEntity<ErrorResponse> handleContentNotFoundException(ContentNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NO_CONTENT, exception), HttpStatus.NO_CONTENT);
    }

    /**
     * Handles the {@link IndexOutOfBoundsException}. Thrown to indicate that an
     * index of some sort (such as to an array, to a string, or to a vector) is out
     * of range.
     *
     * @param exception IndexOutOfBoundsException
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { IndexOutOfBoundsException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleIndexOutOfBoundsException(IndexOutOfBoundsException exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link IllegalArgumentException}. Thrown to indicate that a
     * method has been passed an illegal or inappropriate argument.
     *
     * @param exception IllegalArgumentException
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, exception);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link PropertyReferenceException}. Exception being thrown when
     * creating PropertyPath instances.
     *
     * @param exception PropertyReferenceException
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { PropertyReferenceException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handlePropertyReferenceException(PropertyReferenceException exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception), new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link ConstraintViolationException} thrown when validating
     * fields of a class
     *
     * @param exception the {@code ConstraintViolationException}
     * @return a {@code ResponseEntity} instance with the detailed error
     */
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST);
        response.setMessage("Constraint Violation (Validation error)");
        exception.getConstraintViolations().forEach(violation -> response.addError(violation));

        return new ResponseEntity<>(response, new HttpHeaders(), response.getHttpStatus());
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
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST);
        response.setMessage(String.format("Could not find the method %s for the URL %s", exception.getHttpMethod(),
                exception.getRequestURL()));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link MethodArgumentNotValidException}. Triggered when an object
     * fails {@link javax.validation.Valid} validation.
     *
     * @param ex      MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity instance
     */
    @Override
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST);
        response.setMessage("Validation error");

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> response.addError(fieldError));

        List<ObjectError> objectErrors = ex.getBindingResult().getGlobalErrors();
        objectErrors.forEach(objectError -> response.addError(objectError));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
