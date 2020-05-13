package org.codejudge.sb.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LeadStatusOutput {

	@JsonProperty("status")
	private String status;
	@JsonProperty("communication")
	private String communication;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LeadStatusOutput [status=" + status + ", communication=" + communication + "]";
	}

}
