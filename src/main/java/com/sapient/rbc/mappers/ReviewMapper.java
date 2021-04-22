package com.sapient.rbc.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.entity.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

	
	
	@Mapping(target ="reviewId", source="id")
	@Mapping(target ="dueForReview", dateFormat = "dd/MM/yyyy")
	@Mapping(target ="lastUpdated", dateFormat = "dd/MM/yyyy")
	ReviewDto mapReviewToReviewDto(Review review);

	@InheritInverseConfiguration
	Review mapReviewDtoToReview(ReviewDto reviewDto);

	List<ReviewDto> mapReviewListToReviewDtoList(List<Review> reviewList);

	List<Review> mapReviewDtoListToReviewList(List<ReviewDto> reviewDtoList);
}
