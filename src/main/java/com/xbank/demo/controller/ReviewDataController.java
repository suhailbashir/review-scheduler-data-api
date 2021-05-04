package com.xbank.demo.controller;

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

import com.xbank.demo.constants.AppStatus;
import com.xbank.demo.dto.BaseResponse;
import com.xbank.demo.dto.ListReviewDtoResponse;
import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.dto.ReviewDtoResponse;
import com.xbank.demo.dto.SearchCriteria;
import com.xbank.demo.service.ReviewDataService;

@RestController
@RequestMapping("/review-scheduler-data-api")
@CrossOrigin(origins = "*")
public class ReviewDataController {
	
	@Autowired
	ReviewDataService reviewDataService;

	@PostMapping("/review")
	public ResponseEntity<BaseResponse> saveReview(@RequestBody @Valid ReviewDto reviewDto) {
		
		reviewDto = reviewDataService.saveReview(reviewDto);
		ReviewDtoResponse response = ReviewDtoResponse.builder()
												 .reviewDto(reviewDto)
												 .status(AppStatus.SUCCESS.name())
												 .build();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/review/{id}")
	public ResponseEntity<BaseResponse> findReviewById(@PathVariable(name = "id") Long id)  {
		
		ReviewDto reviewDto=reviewDataService.findReviewById(id);
		BaseResponse response = ReviewDtoResponse.builder()
		 										 .reviewDto(reviewDto)
		 										 .status(AppStatus.SUCCESS.name())
		 										 .build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/review")
	public ResponseEntity<BaseResponse> findAllReviews() {
		
		BaseResponse response=ListReviewDtoResponse.builder()
													.status(AppStatus.SUCCESS.name())
													.reviews(reviewDataService.findAllReviews())
													.build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/review/{id}")
	public ResponseEntity<BaseResponse> updateReview(@RequestBody @Valid ReviewDto reviewDto, @PathVariable Long id)  {
		
		reviewDto= reviewDataService.updateReview(reviewDto, id);
		BaseResponse response = ReviewDtoResponse.builder()
				 								 .reviewDto(reviewDto)
				 								 .status(AppStatus.SUCCESS.name())
				 								 .build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/review/{id}")
	public ResponseEntity<BaseResponse> deleteReviewById(@PathVariable(name = "id") Long id)  {
		
		BaseResponse response=ListReviewDtoResponse.builder()
												   .status(AppStatus.SUCCESS.name())
												   .reviews(reviewDataService.deleteReviewById(id))
												   .build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/reviews")
	public ResponseEntity<BaseResponse> getAllReviewsWithPostFilters(@RequestBody @Valid SearchCriteria criteria) {
		
		BaseResponse response=ListReviewDtoResponse.builder()
												   .status(AppStatus.SUCCESS.name())
												   .reviews(reviewDataService.findAllReviewsWithFilters(criteria))
												   .build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
