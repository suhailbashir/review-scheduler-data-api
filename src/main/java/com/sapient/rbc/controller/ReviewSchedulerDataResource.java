package com.sapient.rbc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.rbc.constants.AppStatus;
import com.sapient.rbc.dto.BaseResponse;
import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.dto.SearchCriteria;
import com.sapient.rbc.exception.DuplicateReviewException;
import com.sapient.rbc.exception.ReviewNotFoundException;
import com.sapient.rbc.service.ReviewDataService;

@RestController
@RequestMapping("/review-scheduler-data-api")
@CrossOrigin(origins = "*")
public class ReviewSchedulerDataResource {
	
	@Autowired
	ReviewDataService reviewDataService;

	@PostMapping("/review")
	public ResponseEntity<ReviewDto> saveReview(@RequestBody @Valid ReviewDto reviewDto) throws DuplicateReviewException {
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDataService.saveReview(reviewDto));
	}

	@GetMapping("/review/{id}")
	public ResponseEntity<ReviewDto> findReviewById(@PathVariable(name = "id") Long id) throws ReviewNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(reviewDataService.findReviewById(id));
	}

	@GetMapping("/review")
	public ResponseEntity<BaseResponse> findAllReviews() {
		
		BaseResponse response=BaseResponse.builder()
				.status(AppStatus.SUCCESS.name())
				.data(reviewDataService.findAllReviews())
				.build();
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/review/{id}")
	public ResponseEntity<ReviewDto> updateReview(@RequestBody @Valid ReviewDto reviewDto, @PathVariable Long id) throws ReviewNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(reviewDataService.updateReview(reviewDto, id));
	}

	@DeleteMapping("/review/{id}")
	public ResponseEntity<BaseResponse> deleteReviewById(@PathVariable(name = "id") Long id) throws ReviewNotFoundException {
		
		BaseResponse response=BaseResponse.builder()
				.status(AppStatus.SUCCESS.name())
				.data(reviewDataService.deleteReviewById(id))
				.build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/reviews")
	public ResponseEntity<BaseResponse> getAllReviewsWithPostFilters(@RequestBody @Valid SearchCriteria criteria)throws ReviewNotFoundException {
		
		BaseResponse response=BaseResponse.builder()
											.status(AppStatus.SUCCESS.name())
											.data(reviewDataService.findAllReviewsWithFilters(criteria))
											.build();
				
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
