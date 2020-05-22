package com.poc.person.model;

public enum Gender {
	
	M("Male"),
	F("Female");
	
	private String value;
	
	private Gender(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
