package com.prokarma.producer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Address
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-05T04:14:20.333Z")

public class Address {
	@JsonProperty("addressLine1")
	private String addressLine1 = null;

	@JsonProperty("addressLine2 ")
	private String addressLine2_ = null;

	@JsonProperty("street")
	private String street = null;

	@JsonProperty("postalCode")
	private Integer postalCode = null;

	public Address addressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
		return this;
	}

	/**
	 * User's first line address.
	 * 
	 * @return addressLine1
	 **/
	@Size(max = 50)
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public Address addressLine2_(String addressLine2_) {
		this.addressLine2_ = addressLine2_;
		return this;
	}

	/**
	 * User's Second line address.
	 * 
	 * @return addressLine2_
	 **/
	public String getAddressLine2_() {
		return addressLine2_;
	}

	public void setAddressLine2_(String addressLine2_) {
		this.addressLine2_ = addressLine2_;
	}

	public Address street(String street) {
		this.street = street;
		return this;
	}

	/**
	 * User's street.
	 * 
	 * @return street
	 **/

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Address postalCode(Integer postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	/**
	 * User's postal code of Address
	 * 
	 * @return postalCode
	 **/

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Address address = (Address) o;
		return Objects.equals(this.addressLine1, address.addressLine1)
				&& Objects.equals(this.addressLine2_, address.addressLine2_)
				&& Objects.equals(this.street, address.street) && Objects.equals(this.postalCode, address.postalCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2_, street, postalCode);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Address {\n");

		sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
		sb.append("    addressLine2_: ").append(toIndentedString(addressLine2_)).append("\n");
		sb.append("    street: ").append(toIndentedString(street)).append("\n");
		sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
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
