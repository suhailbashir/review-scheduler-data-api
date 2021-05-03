package com.xbank.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
import com.xbank.demo.dto.Sort;
import com.xbank.demo.entity.Review;
import com.xbank.demo.exception.ReviewNotFoundException;
import com.xbank.demo.mappers.ReviewMapper;
import com.xbank.demo.repository.ReviewCriteriaRepositoryImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CriteriaTests {

	@MockBean
	private EntityManager entityManager;

	@MockBean
	Environment env;

	@MockBean
	ReviewMapper mapper;

	@InjectMocks
	private ReviewCriteriaRepositoryImpl reviewCriteriaRepositoryImpl;

	@Test
	void findAllReviewsWithFilters() throws ReviewNotFoundException {
		Sort sort = Sort.builder().direction("ASC").sortBy("reviewName").build();
		SearchCriteria searchCriteria = SearchCriteria.builder().pageNumber(0).pageSize(1).sort(sort).build();
		Review review=ObjectBuilderUtility.createReview();
		List<ReviewDto> reviewsDto=ObjectBuilderUtility.createReviewDtoList();
		List<Review> reviews = ObjectBuilderUtility.createReviewList();
		Root<Review> root = Mockito.mock(Root.class);
		Root<Review> countRoot = Mockito.mock(Root.class);
		CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
		CriteriaQuery<Review> criteriaQuery = Mockito.mock(CriteriaQuery.class);
		CriteriaQuery<Long> criteriaQueryLong = Mockito.mock(CriteriaQuery.class);
		
		TypedQuery<Review> typedQuery = Mockito.mock(TypedQuery.class);
		TypedQuery<Long> typedQueryLong = Mockito.mock(TypedQuery.class);

		Mockito.when(criteriaBuilder.createQuery(Review.class)).thenReturn(criteriaQuery);
		Mockito.when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQueryLong);
		
		Mockito.when(criteriaQuery.from(Review.class)).thenReturn(root);
		
		Mockito.when(criteriaQueryLong.from(Review.class)).thenReturn(countRoot);

		//Mockito.when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
		
		Mockito.when(criteriaQueryLong.select(criteriaBuilder.count(countRoot))).thenReturn(criteriaQueryLong);
		
	//	Mockito.when(typedQuery.getSingleResult()).thenReturn(review);
		Mockito.when(typedQueryLong.getSingleResult()).thenReturn(2L);
		Mockito.when(typedQuery.getResultList()).thenReturn(reviews);

		Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
		Mockito.when(entityManager.createQuery(criteriaQueryLong)).thenReturn(typedQueryLong);

		Mockito.when(mapper.mapReviewListToReviewDtoList(Mockito.any())).thenReturn(reviewsDto);

		List<ReviewDto>expectedReviewDtos= reviewCriteriaRepositoryImpl.findAllReviewsWithFilters(searchCriteria);
		
		expectedReviewDtos.forEach(dto -> {

			assertThat(dto).hasFieldOrPropertyWithValue("reviewId", 1L)
					.hasFieldOrPropertyWithValue("reviewName", "Review 1")
					.hasFieldOrPropertyWithValue("dueForReview", "01/01/2020")
					.hasFieldOrPropertyWithValue("frequency", "Daily")
					.hasFieldOrPropertyWithValue("reviewType", "Normal")
					.hasFieldOrPropertyWithValue("lastUpdated", "01/01/2020");

		});
	}

}
