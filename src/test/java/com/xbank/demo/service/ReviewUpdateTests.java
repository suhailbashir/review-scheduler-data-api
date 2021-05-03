package com.xbank.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xbank.demo.ObjectBuilderUtility;
import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.entity.Review;
import com.xbank.demo.exception.ReviewNotFoundException;
import com.xbank.demo.mappers.ReviewMapper;
import com.xbank.demo.mappers.ReviewMapperImpl;
import com.xbank.demo.repository.ReviewRepository;
import com.xbank.demo.service.ReviewDataServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ReviewUpdateTests {
	@MockBean
	private ReviewRepository reviewRepository;

	@MockBean
	Environment env;

	@MockBean
	ReviewMapper mapper;

	@InjectMocks
	private ReviewMapperImpl reviewMapperImpl;

	@InjectMocks
	private ReviewDataServiceImpl reviewDataServiceImpl;

	@Test
	void updateReviewTestSuccess() throws ReviewNotFoundException {

		Review review = ObjectBuilderUtility.createReview();
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();

		Mockito.when(reviewRepository.findById(Mockito.any())).thenReturn(Optional.of(review));
		Mockito.when(env.getProperty(Mockito.any())).thenReturn("Review not found");
		Mockito.when(mapper.mapReviewDtoToReview(Mockito.any())).thenReturn(review);
		Mockito.when(reviewRepository.save(Mockito.any())).thenReturn(review);
		Mockito.when(mapper.mapReviewToReviewDto(Mockito.any())).thenReturn(reviewDto);

		reviewDto.setReviewName("UpdateReview");
		ReviewDto expectedReviewDto = reviewDataServiceImpl.updateReview(reviewDto, 1L);
		assertThat(reviewDto.getReviewName()).isEqualTo(expectedReviewDto.getReviewName());

	}

	@Test
	void updateReviewTestFailure() {
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();

		Mockito.when(reviewRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));

		assertThrows(ReviewNotFoundException.class, () -> {
			reviewDataServiceImpl.updateReview(reviewDto, 1L);
		});

	}

}