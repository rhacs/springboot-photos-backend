package cl.rhacs.springboot.photos.models.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
public class DetailedError {

    // Attributes
    // -----------------------------------------------------------------------------------------

    private String field;
    private String message;
    private String objectName;
    private Object rejectedValue;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new and empty {@link DetailedError}
     */
    public DetailedError() {

    }

    /**
     * Creates a new {@link DetailedError} given a field name and a detail message
     *
     * @param field   field name
     * @param message detail message
     */
    public DetailedError(final String field, final String message) {
        this.field = field;
        this.message = message;
    }

    /**
     * Creates a new {@link DetailedError} given a field, message, objectName and a
     * rejected value
     *
     * @param field         the field name
     * @param message       the detail message
     * @param objectName    the object name
     * @param rejectedValue the rejected value
     */
    public DetailedError(final String field, final String message, final String objectName,
            final Object rejectedValue) {
        this(field, message);
        this.objectName = objectName;
        this.rejectedValue = rejectedValue;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the objectName
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * @return the rejectedValue
     */
    public Object getRejectedValue() {
        return rejectedValue;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param field the field to set
     */
    public void setField(final String field) {
        this.field = field;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @param objectName the objectName to set
     */
    public void setObjectName(final String objectName) {
        this.objectName = objectName;
    }

    /**
     * @param rejectedValue the rejectedValue to set
     */
    public void setRejectedValue(final Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    // Inheritances (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((field == null) ? 0 : field.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((objectName == null) ? 0 : objectName.hashCode());
        result = prime * result + ((rejectedValue == null) ? 0 : rejectedValue.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        final DetailedError other = (DetailedError) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (field == null) {
            if (other.field != null)
                return false;
        } else if (!field.equals(other.field))
            return false;

        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;

        if (objectName == null) {
            if (other.objectName != null)
                return false;
        } else if (!objectName.equals(other.objectName))
            return false;

        if (rejectedValue == null) {
            if (other.rejectedValue != null)
                return false;
        } else if (!rejectedValue.equals(other.rejectedValue))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "DetailedError [field=" + field + ", message=" + message + ", objectName=" + objectName
                + ", rejectedValue=" + rejectedValue + "]";
    }

}
