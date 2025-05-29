package com.productsapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {

	ELECTRONICS,
	BOOKS,
	CLOTHING;
	
	@JsonValue
	public String toLower() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static Category fromValue(String value) {
		return Category.valueOf(value.toUpperCase());
	}
}
