package com.sapient.rbc.dto;

import lombok.Data;

@Data
public class SearchCriteria {

	private int pageNumber = 0;
    private int pageSize = 10;
    private Sort sort;
    
}
