package com.xbank.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.dto.SearchCriteria;
import com.xbank.demo.entity.Review;
import com.xbank.demo.exception.DuplicateReviewException;
import com.xbank.demo.exception.ReviewExceptionMessageConstants;
import com.xbank.demo.exception.ReviewNotFoundException;
import com.xbank.demo.mappers.ReviewMapper;
import com.xbank.demo.repository.ReviewRepository;

@Service
public class ReviewDataServiceImpl implements ReviewDataService {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ReviewMapper reviewMapper;

	@Autowired
	Environment environment;

	@Override
	public ReviewDto saveReview(ReviewDto reviewDto) throws DuplicateReviewException {

		Optional<Review> optionalReview = reviewRepository.findById(reviewDto.getReviewId());
		if (optionalReview.isPresent()) {
			throw new DuplicateReviewException(HttpStatus.BAD_REQUEST.value(),
					environment.getProperty(ReviewExceptionMessageConstants.DUPLICATE_REVIEW_EXCEPTION));
		}

		Review review = reviewRepository.save(reviewMapper.mapReviewDtoToReview(reviewDto));
		return reviewMapper.mapReviewToReviewDto(review);
	}

	@Override
	public ReviewDto findReviewById(Long id) throws ReviewNotFoundException {
		
		return reviewMapper
				.mapReviewToReviewDto(reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(HttpStatus.NOT_FOUND.value(),
						environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION))));
	}

	@Override
	public List<ReviewDto> findAllReviews() {

		return reviewMapper.mapReviewListToReviewDtoList(reviewRepository.findAll());
	}
	
	@Override
	public ReviewDto updateReview(ReviewDto updatedReviewDto, Long id) throws ReviewNotFoundException  {

		Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(HttpStatus.NOT_FOUND.value(),
				environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION)));

		if (review != null) {
			review = reviewRepository.save(reviewMapper.mapReviewDtoToReview(updatedReviewDto));
		}
		return reviewMapper.mapReviewToReviewDto(review);
	}
	
	@Override
	public List<ReviewDto> deleteReviewById(Long id) throws ReviewNotFoundException  {

		reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(HttpStatus.NOT_FOUND.value(),
				environment.getProperty(ReviewExceptionMessageConstants.REVIEW_NOT_FOUND_EXCEPTION)));
		
		reviewRepository.deleteById(id);
		return findAllReviews();
	}

	@Override
	public List<ReviewDto> findAllReviewsWithFilters(SearchCriteria criteria) throws ReviewNotFoundException {
		return  reviewRepository.findAllReviewsWithFilters(criteria);
	}

}
