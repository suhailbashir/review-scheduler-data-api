package com.sapient.rbc.repository;

import java.util.List;

import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.dto.SearchCriteria;
import com.sapient.rbc.exception.ReviewNotFoundException;

public interface ReviewCriteriaRepository {

	public List<ReviewDto> findAllReviewsWithFilters(SearchCriteria reviewSearchCriteria) throws ReviewNotFoundException;
}
