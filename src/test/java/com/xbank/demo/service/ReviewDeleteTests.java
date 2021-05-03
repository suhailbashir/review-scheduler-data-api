package com.xbank.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
import com.xbank.demo.entity.Review;
import com.xbank.demo.exception.ReviewNotFoundException;
import com.xbank.demo.mappers.ReviewMapper;
import com.xbank.demo.mappers.ReviewMapperImpl;
import com.xbank.demo.repository.ReviewRepository;
import com.xbank.demo.service.ReviewDataServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ReviewDeleteTests {

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
	void deleteReviewTestSuccess() throws ReviewNotFoundException {
		Review review = ObjectBuilderUtility.createReview();

		when(reviewRepository.findById(Mockito.any())).thenReturn(Optional.of(review));
		when(env.getProperty(Mockito.any())).thenReturn(null);
		doNothing().when(reviewRepository).deleteById(Mockito.any());

		List<ReviewDto> expectedreviewDtoList = reviewDataServiceImpl.deleteReviewById(1L);
		assertThat(expectedreviewDtoList).isNotNull();
	}

	@Test
	void deleteReviewTestFailure() {

		when(reviewRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		when(env.getProperty(Mockito.any())).thenReturn("Review Not Found Exception");
		doNothing().when(reviewRepository).deleteById(Mockito.any());

		assertThrows(ReviewNotFoundException.class, () -> {
			reviewDataServiceImpl.deleteReviewById(1L);
		});
	}
}
