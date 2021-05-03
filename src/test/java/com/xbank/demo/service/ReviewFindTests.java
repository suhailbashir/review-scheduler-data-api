package com.xbank.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xbank.demo.ObjectBuilderUtility;
import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.dto.SearchCriteria;
import com.xbank.demo.entity.Review;
import com.xbank.demo.exception.ReviewNotFoundException;
import com.xbank.demo.mappers.ReviewMapper;
import com.xbank.demo.repository.ReviewRepository;
import com.xbank.demo.service.ReviewDataServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ReviewFindTests {

	@MockBean
	private ReviewRepository reviewRepository;

	@MockBean
	Environment env;

	@MockBean
	ReviewMapper mapper;

	@InjectMocks
	private ReviewDataServiceImpl reviewDataServiceImpl;

	@Test
	void findReviewByIdTestSuccess() throws ReviewNotFoundException {
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
	void findAllReviewsWithFiltersSuccess() throws ReviewNotFoundException {
		List<ReviewDto>listOfRelistOfReviewDtos=ObjectBuilderUtility.createReviewDtoList();
		Mockito.when(reviewRepository.findAllReviewsWithFilters(Mockito.any())).thenReturn(listOfRelistOfReviewDtos);
	
		List<ReviewDto> listOfReviewDtosExpected = reviewDataServiceImpl.findAllReviewsWithFilters(SearchCriteria.builder().build());
		assertEquals(listOfRelistOfReviewDtos, listOfReviewDtosExpected);
	}


	@Test
	void findAllReviewsWithFiltersFailure() throws ReviewNotFoundException {

		Mockito.when(reviewRepository.findAllReviewsWithFilters(Mockito.any())).thenReturn(null);
		
		List<ReviewDto> listOfReviewDtos = reviewDataServiceImpl.findAllReviewsWithFilters(null);
		assertThat(listOfReviewDtos).isNull();
	}


}