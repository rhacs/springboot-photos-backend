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

    private final Date timestamp;
    private HttpStatus httpStatus;
    private int httpStatusCode;
    private String message;
    private final Set<DetailedError> detailedErrors;

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
    public ErrorResponse(final HttpStatus status) {
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
    public ErrorResponse(final HttpStatus status, final Exception exception) {
        this(status);

        this.message = (exception.getLocalizedMessage() == null) ? exception.getMessage()
                : exception.getLocalizedMessage();
    }

    // Methods
    // -----------------------------------------------------------------------------------------

    public void addError(final String field, final String message) {
        detailedErrors.add(new DetailedError(field, message));
    }

    public void addError(final String field, final String message, final String objectName,
            final Object rejectedValue) {
        detailedErrors.add(new DetailedError(field, message, objectName, rejectedValue));
    }

    public void addError(final FieldError fieldError) {
        addError(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getObjectName(),
                fieldError.getRejectedValue());
    }

    public void addError(final ObjectError objectError) {
        addError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    public void addError(final ConstraintViolation<?> violation) {
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
    public void setHttpStatus(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * @param httpStatusCode the httpStatusCode to set
     */
    public void setHttpStatusCode(final int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
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
