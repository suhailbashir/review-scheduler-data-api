package com.sapient.rbc.dto;

public enum SortBy {
	REVIEW_NAME("reviewName"), FREQUENCY("frequency");

	private final String sortByCode;

	private SortBy(String sortByCode) {
		this.sortByCode = sortByCode;
	}

	public String getSortBy() {
		return this.sortByCode;
	}
}
