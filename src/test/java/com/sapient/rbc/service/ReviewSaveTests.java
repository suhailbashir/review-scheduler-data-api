package com.sapient.rbc.service;

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

import com.sapient.rbc.ObjectBuilderUtility;
import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.entity.Review;
import com.sapient.rbc.exception.DuplicateReviewException;
import com.sapient.rbc.mappers.ReviewMapper;
import com.sapient.rbc.mappers.ReviewMapperImpl;
import com.sapient.rbc.repository.ReviewRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ReviewSaveTests {

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
	void savereviewTestSuccess() throws DuplicateReviewException {

		Review review = ObjectBuilderUtility.createReview();
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();

		Mockito.when(reviewRepository.save(Mockito.any())).thenReturn(review);
		Mockito.when(env.getProperty(Mockito.any())).thenReturn("Duplicate");
		Mockito.when(mapper.mapReviewToReviewDto(Mockito.any())).thenReturn(reviewDto);

		ReviewDto expectedreviewDto = reviewDataServiceImpl.saveReview(reviewDto);
		Review reviewExpected = reviewMapperImpl.mapReviewDtoToReview(reviewDto);

		assertThat(expectedreviewDto.getReviewId()).isEqualTo(review.getId());
		assertThat(reviewExpected.getId()).isEqualTo(reviewDto.getReviewId());
		assertThat(review).isEqualTo(reviewExpected);

	}

	@Test
	void savereviewTestFailure() {
		Review review = ObjectBuilderUtility.createReview();
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();

		Mockito.when(reviewRepository.findById(Mockito.any())).thenReturn(Optional.of(review));

		assertThrows(DuplicateReviewException.class, () -> {
			reviewDataServiceImpl.saveReview(reviewDto);
		});

	}

}