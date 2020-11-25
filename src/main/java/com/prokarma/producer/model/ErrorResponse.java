package com.prokarma.producer.model;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * As defined in
 * http://api-standards.apps.px-npe01.cf.prokarma.com/http/status-codes/ErrorResponse-response-format/
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2020-11-25T12:48:48.427Z")



public class ErrorResponse {
    @JsonProperty("status")
    private String status = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("error_type")
    private String errorType = null;

    public ErrorResponse status(String status) {
        this.status = status;
        return this;
    }

    /**
     * Value should be error
     * 
     * @return status
     **/
    @NotNull
    @Pattern(regexp = "^[\\S ]+$")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * A human-readable message describing the ErrorResponse.
     * 
     * @return message
     **/
    @NotNull
    @Pattern(regexp = "^[\\S ]+$")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse errorType(String errorType) {
        this.errorType = errorType;
        return this;
    }

    /**
     * Type of exception.
     * 
     * @return errorType
     **/

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorResponse errorResponse = (ErrorResponse) o;
        return Objects.equals(this.status, errorResponse.status)
                && Objects.equals(this.message, errorResponse.message)
                && Objects.equals(this.errorType, errorResponse.errorType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, errorType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorResponse {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    errorType: ").append(toIndentedString(errorType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

