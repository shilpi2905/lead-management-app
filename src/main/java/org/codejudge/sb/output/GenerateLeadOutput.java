package org.codejudge.sb.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerateLeadOutput {

	@JsonProperty("id")
	private Integer leadId;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("mobile")
	private Long mobile;
	@JsonProperty("email")
	private String email;
	@JsonProperty("location_type")
	private String locationType;
	@JsonProperty("location_string")
	private String location;
	@JsonProperty("status")
	private StatusCode status;
	@JsonProperty("communication")
	private String communication;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getLeadId() {
		return leadId;
	}

	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public StatusCode getStatus() {
		return status;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GenerateLeadOutput [leadId=" + leadId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobile=" + mobile + ", email=" + email + ", locationType=" + locationType + ", location="
				+ location + ", status=" + status + "]";
	}

}
