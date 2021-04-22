package com.sapient.rbc.controller;

import java.util.List;

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

import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.dto.SearchCriteria;
import com.sapient.rbc.exception.ReviewNotFoundException;
import com.sapient.rbc.service.ReviewDataService;

@RestController
@RequestMapping("/review-scheduler-data-api")
@CrossOrigin(origins = "*")
public class ReviewSchedulerDataResource {

	@Autowired
	ReviewDataService reviewDataService;

	@PostMapping("/review")
	public ResponseEntity<ReviewDto> saveReview(@RequestBody @Valid ReviewDto reviewDto)  {
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDataService.saveReview(reviewDto));
	}

	@GetMapping("/review/{id}")
	public ResponseEntity<ReviewDto> findReviewById(@PathVariable(name = "id") Long id) throws ReviewNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(reviewDataService.findReviewById(id));
	}

	@GetMapping("/review")
	public ResponseEntity<List<ReviewDto>> findAllReviews() throws ReviewNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(reviewDataService.findAllReviews());
	}

	@PutMapping("/review/{id}")
	public ResponseEntity<ReviewDto> updateReview(@RequestBody @Valid ReviewDto reviewDto, @PathVariable Long id) throws ReviewNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(reviewDataService.updateReview(reviewDto, id));
	}

	@DeleteMapping("/review/{id}")
	public ResponseEntity<List<ReviewDto>> deleteReviewById(@PathVariable(name = "id") Long id) throws ReviewNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(reviewDataService.deleteReviewById(id));
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<List<ReviewDto>> getAllReviewsWithPostFilters(@RequestBody SearchCriteria criteria) {
		return ResponseEntity.status(HttpStatus.OK).body(reviewDataService.findAllReviewsWithFilters(criteria));
	}
	

}
