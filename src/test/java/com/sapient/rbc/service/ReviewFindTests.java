package com.sapient.rbc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sapient.rbc.ObjectBuilderUtility;
import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.entity.Review;
import com.sapient.rbc.exception.ReviewNotFoundException;
import com.sapient.rbc.mappers.ReviewMapper;
import com.sapient.rbc.repository.ReviewCriteriaRepository;
import com.sapient.rbc.repository.ReviewRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ReviewFindTests {

	@MockBean
	private ReviewRepository reviewRepository;

	@MockBean
	private ReviewCriteriaRepository reviewCriteriaRepository;

	@MockBean
	Environment env;

	@MockBean
	ReviewMapper mapper;

	@InjectMocks
	private ReviewDataServiceImpl reviewDataServiceImpl;

	@Test
	void findReviewByIdTestSuccess() {
		Review review = ObjectBuilderUtility.createReview();
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();

		Mockito.when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
		Mockito.when(mapper.mapReviewToReviewDto(Mockito.any())).thenReturn(reviewDto);

		ReviewDto expectedReviewDto = reviewDataServiceImpl.findReviewById(1L);
		assertThat(expectedReviewDto.getReviewId()).isEqualTo(reviewDto.getReviewId());
	}

	@Test
	void findReviewByIdTestFailure() {
		Mockito.when(reviewRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

		assertThrows(ReviewNotFoundException.class, () -> {
			reviewDataServiceImpl.findReviewById(1L);
		});
	}

	@Test
	void findAllReviewsSuccess() {

		List<Review> listOfReviews = ObjectBuilderUtility.createReviewList();

		Mockito.when(reviewRepository.findAll()).thenReturn(listOfReviews);

		List<ReviewDto> expextedReviewDtosList = reviewDataServiceImpl.findAllReviews();

		expextedReviewDtosList.forEach(dto -> {

			assertThat(dto).hasFieldOrPropertyWithValue("reviewId", 1L)
					.hasFieldOrPropertyWithValue("reviewName", "Review 1")
					.hasFieldOrPropertyWithValue("dueForReview", "01/01/2020")
					.hasFieldOrPropertyWithValue("frequency", "Daily")
					.hasFieldOrPropertyWithValue("reviewType", "Normal")
					.hasFieldOrPropertyWithValue("lastUpdated", "01/01/2020");

		});
	}

	@Test
	void findAllReviewsWithFiltersSuccess() {

		Mockito.when(reviewCriteriaRepository.findAllReviewsWithFilters(Mockito.any())).thenReturn(null);
		List<ReviewDto> listOfReviewDtos = reviewDataServiceImpl.findAllReviewsWithFilters(null);
		assertThat(listOfReviewDtos).isNull();
	}

}