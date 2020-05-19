package cl.rhacs.springboot.photos.models.errors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

    // Attributes
    // -----------------------------------------------------------------------------------------

    private Date timestamp;
    private HttpStatus httpStatus;
    private int httpStatusCode;
    private String message;
    private Set<DetailedError> detailedErrors;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new {@link ErrorResponse} with the timestamp initialized
     */
    public ErrorResponse() {
        timestamp = new Date();
        message = "Unknown error";
        detailedErrors = new HashSet<>();
    }

    /**
     * Creates a new {@link ErrorResponse} given a {@link HttpStatus}
     *
     * @param status the Http Status
     */
    public ErrorResponse(HttpStatus status) {
        this();
        this.httpStatus = status;
        this.httpStatusCode = status.value();
    }

    /**
     * Creates a new {@link ErrorResponse} given a {@link HttpStatus} and a
     * {@link Exception}
     *
     * @param status    the Http Status
     * @param exception the Exception
     */
    public ErrorResponse(HttpStatus status, Exception exception) {
        this(status);

        this.message = (exception.getLocalizedMessage() == null) ? exception.getMessage()
                : exception.getLocalizedMessage();
    }

    // Methods
    // -----------------------------------------------------------------------------------------

    /**
     * Adds a {@link DetailedError} to the error list given a field and a message
     *
     * @param field   name of the field affected
     * @param message the detail message
     */
    public void addError(String field, String message) {
        detailedErrors.add(new DetailedError(field, message));
    }

    /**
     * Adds a {@link DetailedError} to the error list given a field, a message, an
     * object name and a rejected value
     *
     * @param field         name of the field affected
     * @param message       the detail message
     * @param objectName    the parent class of the field
     * @param rejectedValue the rejected value
     */
    public void addError(String field, String message, String objectName, Object rejectedValue) {
        detailedErrors.add(new DetailedError(field, message, objectName, rejectedValue));
    }

    /**
     * Adds a {@link FieldError} to the error list
     *
     * @param fieldError the field error
     */
    public void addError(FieldError fieldError) {
        addError(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getObjectName(),
                fieldError.getRejectedValue());
    }

    /**
     * Adds an {@link ObjectError} to the error list
     *
     * @param objectError the object error
     */
    public void addError(ObjectError objectError) {
        addError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    /**
     * Adds a {@link ConstraintViolation} to the error list
     *
     * @param violation the constraint violation
     */
    public void addError(ConstraintViolation<?> violation) {
        addError(violation.getRootBeanClass().getSimpleName(),
                ((PathImpl) violation.getPropertyPath()).getLeafNode().asString(), violation.getMessage(),
                violation.getInvalidValue());
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return the httpStatusCode
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the detailedErrors
     */
    public Set<DetailedError> getDetailedErrors() {
        return detailedErrors;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * @param httpStatusCode the httpStatusCode to set
     */
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    // Inheritances
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "ErrorResponse [timestamp=" + timestamp + ", httpStatus=" + httpStatus + ", httpStatusCode="
                + httpStatusCode + ", message=" + message + ", detailedErrors=" + detailedErrors + "]";
    }

}
