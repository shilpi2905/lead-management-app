package org.codejudge.sb.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LocationType {

	COUNTRY("Country"), CITY("City"), ZIP("Zip");
	private String value;

	private LocationType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static LocationType fromValue(String text) {
		for (LocationType b : LocationType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
