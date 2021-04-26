package com.sapient.rbc.dto;

import java.io.Serializable;

public enum SortBy implements Serializable  {
	
	REVIEW_NAME("reviewName"), FREQUENCY("frequency");

	private final String sortByCode;

	private SortBy(String sortByCode) {
		this.sortByCode = sortByCode;
	}

	public String getSortBy() {
		return this.sortByCode;
	}
}
