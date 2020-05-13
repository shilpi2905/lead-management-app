package org.codejudge.sb.input;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeadInput {

	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("mobile")
	private Long mobile;
	@JsonProperty("email")
	private String email;
	@JsonProperty("location_type")
	private LocationType locationType;
	@JsonProperty("location_string")
	private String location;

	@NotNull(message = "first_name must not be null")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotNull(message = "last_name must not be null")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@NotNull(message = "mobile must not be null")
	@Digits(integer = 10, fraction = 0, message = "Invalid mobile number, it should be 10 digit number")
	@Min(value = 1000000000L, message = "Invalid mobile number, it should be 10 digit number")
	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	@NotNull(message = "email must not be null")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull(message = "location_type must not be null and should contain valid values (Country/City/Zip)")
	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	@NotNull(message = "location_string must not be null")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "GenerateLeadInput [firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile
				+ ", email=" + email + ", locationType=" + locationType + ", location=" + location + "]";
	}

}
