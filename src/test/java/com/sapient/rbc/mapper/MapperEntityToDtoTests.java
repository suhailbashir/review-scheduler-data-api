package com.sapient.rbc.mapper;


	import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sapient.rbc.ObjectBuilderUtility;
import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.entity.Review;
import com.sapient.rbc.mappers.ReviewMapperImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MapperEntityToDtoTests {

		@InjectMocks
		private ReviewMapperImpl ReviewMapperImpl;

		@Test
		@DisplayName("Review Success Case")
		void mapReviewToReviewDtoSuccessTest() {

			Review review = ObjectBuilderUtility.createReview();
			ReviewDto dto = ReviewMapperImpl.mapReviewToReviewDto(review);
			assertThat(dto)
			.hasFieldOrPropertyWithValue("reviewId", 1L)
			.hasFieldOrPropertyWithValue("reviewName", "Review 1")
			.hasFieldOrPropertyWithValue("dueForReview", "01/01/2020")
			.hasFieldOrPropertyWithValue("frequency", "Daily")
			.hasFieldOrPropertyWithValue("reviewType", "Normal")
			.hasFieldOrPropertyWithValue("lastUpdated",  "01/01/2020");
		}

		@Test
		@DisplayName("Review Failure Case")
		void mapReviewToReviewDtoFailureReviewTest() {

			Review review = null;
			ReviewDto dto = ReviewMapperImpl.mapReviewToReviewDto(review);
			assertThat(dto).isNull();
			
		}
		
		@Test
		@DisplayName("Review List Success Case")
		void mapReviewListEntityToDtoSuccesTest() {

			List<Review> reviewList = ObjectBuilderUtility.createReviewList();
			List<ReviewDto> reviewDtoList = ReviewMapperImpl.mapReviewListToReviewDtoList(reviewList);
			
			reviewDtoList.forEach(dto->{
				
				assertThat(dto)
				.hasFieldOrPropertyWithValue("reviewId", 1L)
				.hasFieldOrPropertyWithValue("reviewName", "Review 1")
				.hasFieldOrPropertyWithValue("dueForReview", "01/01/2020")
				.hasFieldOrPropertyWithValue("frequency", "Daily")
				.hasFieldOrPropertyWithValue("reviewType", "Normal")
				.hasFieldOrPropertyWithValue("lastUpdated",  "01/01/2020");
			
			});
			
		}
		
		
		@Test
		@DisplayName("Review List Failure Case")
		void mapReviewListDtoToEntityFailureTest() {

			List<Review> reviewList = null;
			List<ReviewDto> reviewDtoList = ReviewMapperImpl.mapReviewListToReviewDtoList(reviewList);
			
			assertThat(reviewDtoList).isNull();
			
		}

	}
