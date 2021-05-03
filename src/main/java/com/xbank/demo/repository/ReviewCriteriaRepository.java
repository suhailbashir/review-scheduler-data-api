package com.xbank.demo.repository;

import java.util.List;

import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.dto.SearchCriteria;
import com.xbank.demo.exception.ReviewNotFoundException;

public interface ReviewCriteriaRepository {

	public List<ReviewDto> findAllReviewsWithFilters(SearchCriteria reviewSearchCriteria) throws ReviewNotFoundException;
}
