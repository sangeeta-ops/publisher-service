package com.prokarma.producer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * As defined in
 * http://api-standards.apps.px-npe01.cf.prokarma.com/http/status-codes/error-response-format/
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-05T04:14:20.333Z")

public class ErrorResponse {
	@JsonProperty("code")
	private String code = null;

	@JsonProperty("userMessage")
	private String userMessage = null;

	@JsonProperty("systemMessage")
	private String systemMessage = null;

	@JsonProperty("detailLink")
	private String detailLink = null;

	public ErrorResponse code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * A succinct, domain-specific, human-readable text string to identify the type
	 * of error for the given status code
	 * 
	 * @return code
	 **/
	@NotNull

	@Pattern(regexp = "^[\\S ]+$")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ErrorResponse userMessage(String userMessage) {
		this.userMessage = userMessage;
		return this;
	}

	/**
	 * A human-readable message describing the error.
	 * 
	 * @return userMessage
	 **/
	@NotNull

	@Pattern(regexp = "^[\\S ]+$")
	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public ErrorResponse systemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
		return this;
	}

	/**
	 * Text that provides a more detailed technical explanation of the error
	 * 
	 * @return systemMessage
	 **/

	public String getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}

	public ErrorResponse detailLink(String detailLink) {
		this.detailLink = detailLink;
		return this;
	}

	/**
	 * link to custom information providing greater detail on error or errors
	 * 
	 * @return detailLink
	 **/

	public String getDetailLink() {
		return detailLink;
	}

	public void setDetailLink(String detailLink) {
		this.detailLink = detailLink;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorResponse error = (ErrorResponse) o;
		return Objects.equals(this.code, error.code) && Objects.equals(this.userMessage, error.userMessage)
				&& Objects.equals(this.systemMessage, error.systemMessage)
				&& Objects.equals(this.detailLink, error.detailLink);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, userMessage, systemMessage, detailLink);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Error {\n");

		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    userMessage: ").append(toIndentedString(userMessage)).append("\n");
		sb.append("    systemMessage: ").append(toIndentedString(systemMessage)).append("\n");
		sb.append("    detailLink: ").append(toIndentedString(detailLink)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
