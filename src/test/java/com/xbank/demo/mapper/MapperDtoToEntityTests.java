package com.xbank.demo.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xbank.demo.ObjectBuilderUtility;
import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.entity.Review;
import com.xbank.demo.mappers.ReviewMapperImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MapperDtoToEntityTests {

	@InjectMocks
	private ReviewMapperImpl ReviewMapperImpl;

	@Test
	@DisplayName("ReviewDto Success Case")
	void mapReviewDtoToReviewSuccessTest() {
		
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();
		Review review = ReviewMapperImpl.mapReviewDtoToReview(reviewDto);
		assertThat(review).hasFieldOrPropertyWithValue("id", 1L)
				.hasFieldOrPropertyWithValue("reviewName", "Review 1")
				.hasFieldOrPropertyWithValue("dueForReview", LocalDate.of(2020, 01, 01))
				.hasFieldOrPropertyWithValue("frequency", "Daily")
				.hasFieldOrPropertyWithValue("reviewType", "Normal")
				.hasFieldOrPropertyWithValue("lastUpdated", LocalDate.of(2020, 01, 01));
	}

	@Test
	@DisplayName("ReviewDto Failure Case")
	void mapReviewDtoToReviewFailureTest() {

		ReviewDto reviewDto = null;
		Review review = ReviewMapperImpl.mapReviewDtoToReview(reviewDto);
		assertThat(review).isNull();

	}
	
	@Test
	@DisplayName("ReviewDto List Success Case")
	void mapReviewListDtoToEntitySuccesTest() {

		List<ReviewDto> reviewDtoList = ObjectBuilderUtility.createReviewDtoList();
		List<Review> reviewList = ReviewMapperImpl.mapReviewDtoListToReviewList(reviewDtoList);

		reviewList.forEach(review -> {

			assertThat(review).hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("reviewName", "Review 1")
			.hasFieldOrPropertyWithValue("dueForReview", LocalDate.of(2020, 01, 01))
			.hasFieldOrPropertyWithValue("frequency", "Daily")
			.hasFieldOrPropertyWithValue("reviewType", "Normal")
			.hasFieldOrPropertyWithValue("lastUpdated", LocalDate.of(2020, 01, 01));
		});

	}
	
	@Test
	@DisplayName("ReviewDto List Failure Case")
	void mapReviewListDtoToEntityFailureTest() {

		List<ReviewDto> reviewDtoList = null;
		List<Review> reviewList = ReviewMapperImpl.mapReviewDtoListToReviewList(reviewDtoList);

		assertThat(reviewList).isNull();

	}

}
