package com.dwb.stuffoflegend.model;

public enum PowerType {

	EX("EX", "exHeader"), SU("SU", "suHeader"), SLA("SLA", "slaHeader");
	private String typeString, headerStyle;

	private PowerType(String typeString, String headerStyle) {
		this.typeString = typeString;
		this.headerStyle = headerStyle;
	}

	public static PowerType fromString(String attributeValue) {
		for (PowerType type : PowerType.values()) {
			if (type.typeString.equals(attributeValue))
				return type;
		}
		return null;
	}

	public String getHeaderStyle() {
		return headerStyle;
	}
}
