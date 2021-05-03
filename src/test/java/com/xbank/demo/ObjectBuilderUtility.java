package com.xbank.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.entity.Review;

public class ObjectBuilderUtility {

	public static Review createReview() {
		return Review.builder()
				.id(1L)
				.reviewName("Review 1")
				.dueForReview(LocalDate.of(2020, 01, 01))
				.frequency("Daily")
				.reviewType("Normal")
				.lastUpdated(LocalDate.of(2020, 01, 01))
				.build();
	}

	public static List<Review> createReviewList() {
		
		List<Review>reviewList=new ArrayList<>();
		for(int i=0;i<5;i++) {
		Review review=Review.builder()
			.id(1L)
			.reviewName("Review 1")
			.dueForReview(LocalDate.of(2020, 01, 01))
			.frequency("Daily")
			.reviewType("Normal")
			.lastUpdated(LocalDate.of(2020, 01, 01))
			.build();
		reviewList.add(review);
		}
		return reviewList;
	}
	
	
	public static ReviewDto createReviewDto() {
		return ReviewDto.builder()
				.reviewId(1L)
				.reviewName("Review 1")
				.dueForReview("01/01/2020")
				.frequency("Daily")
				.reviewType("Normal")
				.lastUpdated("01/01/2020")
				.build();
	}

	public static List<ReviewDto> createReviewDtoList() {
		
		List<ReviewDto>reviewListDto=new ArrayList<>();
		for(int i=0;i<5;i++) {
		ReviewDto reviewDto=ReviewDto.builder()
				.reviewId(1L)
				.reviewName("Review 1")
				.dueForReview("01/01/2020")
				.frequency("Daily")
				.reviewType("Normal")
				.lastUpdated("01/01/2020")
				.build();
		reviewListDto.add(reviewDto);
		}
		return reviewListDto;
	}
	
	
	

}
