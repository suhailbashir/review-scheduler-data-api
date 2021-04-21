package com.sapient.rbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.dto.SearchCriteria;
import com.sapient.rbc.entity.Review;
import com.sapient.rbc.exception.DuplicateReviewException;
import com.sapient.rbc.exception.ReviewExceptionMessageConstants;
import com.sapient.rbc.exception.ReviewNotFoundException;
import com.sapient.rbc.mappers.ReviewMapper;
import com.sapient.rbc.repository.ReviewCriteriaRepository;
import com.sapient.rbc.repository.ReviewRepository;

@Service
public class ReviewDataServiceImpl implements ReviewDataService {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ReviewCriteriaRepository reviewCriteriaRepository;

	
	@Autowired
	ReviewMapper reviewMapper;
	
	@Autowired
	Environment environment;
	
	
	@Override
	public ReviewDto saveReview(ReviewDto reviewDto) throws DuplicateReviewException {
		Review review = null;
		try {
			review = reviewRepository.save(reviewMapper.mapReviewDtoToReview(reviewDto));
		} catch (Exception e) {
			throw new DuplicateReviewException(
					environment.getProperty(ReviewExceptionMessageConstants.DUPLICATE_REVIEW_EXCEPTION));
		}
		return reviewMapper.mapReviewToReviewDto(review);
	}

	@Override
	public ReviewDto findReviewById(Long id) throws ReviewNotFoundException {

		return reviewMapper.mapReviewToReviewDto( reviewRepository.findById(id).orElseThrow(()->new ReviewNotFoundException(
				environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION)
				)));
	}

	@Override
	public List<ReviewDto> findAllReviews() {
		
		return reviewMapper.mapReviewListToReviewDtoList(reviewRepository.findAll());
	}

	@Override
	public ReviewDto updateReview(ReviewDto updatedReviewDto, Long id) throws ReviewNotFoundException {

		Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(
				environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION)));

		if (review != null) {
			review = reviewRepository.save(reviewMapper.mapReviewDtoToReview(updatedReviewDto));
		}
		return reviewMapper.mapReviewToReviewDto(review);
	}

	@Override
	public List<ReviewDto> deleteReviewById(Long id) throws ReviewNotFoundException  {
	
		reviewRepository.findById(id).orElseThrow(()->new ReviewNotFoundException(
				environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION)
				));
		
		return findAllReviews() ;
	}

	@Override
	public Page<ReviewDto> findAllReviewsWithFilters(SearchCriteria criteria) {
		return reviewCriteriaRepository.findAllReviewsWithFilters(criteria);
}
	
}
