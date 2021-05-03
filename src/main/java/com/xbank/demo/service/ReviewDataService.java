package com.xbank.demo.service;

import java.util.List;

import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.dto.SearchCriteria;
import com.xbank.demo.exception.DuplicateReviewException;
import com.xbank.demo.exception.ReviewNotFoundException;

public interface ReviewDataService {

	public ReviewDto saveReview(ReviewDto review) throws DuplicateReviewException;
	public ReviewDto findReviewById(Long id) throws ReviewNotFoundException;
	public List<ReviewDto> findAllReviews();
	public ReviewDto updateReview(ReviewDto updatedReview, Long id) throws ReviewNotFoundException;
	public List<ReviewDto> deleteReviewById(Long id) throws ReviewNotFoundException;
	public List<ReviewDto> findAllReviewsWithFilters(SearchCriteria criteria) throws ReviewNotFoundException;
}
