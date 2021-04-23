package com.sapient.rbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.dto.SearchCriteria;
import com.sapient.rbc.entity.Review;
import com.sapient.rbc.exception.DuplicateReviewException;
import com.sapient.rbc.exception.ReviewExceptionMessageConstants;
import com.sapient.rbc.exception.ReviewNotFoundException;
import com.sapient.rbc.mappers.ReviewMapper;
import com.sapient.rbc.repository.ReviewRepository;

@Service
public class ReviewDataServiceImpl implements ReviewDataService {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ReviewMapper reviewMapper;

	@Autowired
	Environment environment;

	@Override
	public ReviewDto saveReview(ReviewDto reviewDto) {

		Optional<Review> optionalReview = reviewRepository.findById(reviewDto.getReviewId());
		if (optionalReview.isPresent()) {
			throw new DuplicateReviewException(
					environment.getProperty(ReviewExceptionMessageConstants.DUPLICATE_REVIEW_EXCEPTION),HttpStatus.BAD_REQUEST.value());
		}

		Review review = reviewRepository.save(reviewMapper.mapReviewDtoToReview(reviewDto));
		return reviewMapper.mapReviewToReviewDto(review);
	}

	@Override
	public ReviewDto findReviewById(Long id) {

		return reviewMapper
				.mapReviewToReviewDto(reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(
						environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION),HttpStatus.NOT_FOUND.value())));
	}

	@Override
	public List<ReviewDto> findAllReviews() {

		return reviewMapper.mapReviewListToReviewDtoList(reviewRepository.findAll());
	}

	@Override
	public ReviewDto updateReview(ReviewDto updatedReviewDto, Long id)  {

		Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(
				environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION),HttpStatus.NOT_FOUND.value()));

		if (review != null) {
			review = reviewRepository.save(reviewMapper.mapReviewDtoToReview(updatedReviewDto));
		}
		return reviewMapper.mapReviewToReviewDto(review);
	}
	
	@Override
	public List<ReviewDto> deleteReviewById(Long id)  {

		reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(
				environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION),HttpStatus.NOT_FOUND.value()));

		reviewRepository.deleteById(id);
		return findAllReviews();
	}
	
	@Override
	public List<ReviewDto> findAllReviewsWithFilters(SearchCriteria criteria) {
		return  reviewRepository.findAllReviewsWithFilters(criteria);
	}

}
