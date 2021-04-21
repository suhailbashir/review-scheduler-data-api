package com.sapient.rbc.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.dto.SearchCriteria;
import com.sapient.rbc.exception.DuplicateReviewException;
import com.sapient.rbc.exception.ReviewNotFoundException;

public interface ReviewDataService {
	
	public ReviewDto saveReview(ReviewDto review)throws DuplicateReviewException;
	public ReviewDto findReviewById(Long id)throws ReviewNotFoundException;
	public List<ReviewDto> findAllReviews() throws ReviewNotFoundException;
	public ReviewDto updateReview(ReviewDto updatedReview,Long id) throws ReviewNotFoundException;
	public List<ReviewDto>deleteReviewById(Long id)throws ReviewNotFoundException;
	public Page<ReviewDto>findAllReviewsWithFilters(SearchCriteria criteria);
}
