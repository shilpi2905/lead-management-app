package org.codejudge.sb.input;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarkLeadInput {

	@JsonProperty("communication")
	private String communication;

	@NotNull(message = "communication must not be null")
	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	@Override
	public String toString() {
		return "MarkLeadInput [communication=" + communication + "]";
	}
	
}
