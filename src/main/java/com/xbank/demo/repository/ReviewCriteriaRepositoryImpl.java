package com.xbank.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.xbank.demo.constants.ReviewExceptionMessageConstants;
import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.dto.SearchCriteria;
import com.xbank.demo.entity.Review;
import com.xbank.demo.exception.ReviewNotFoundException;
import com.xbank.demo.mappers.ReviewMapper;

@Repository
public class ReviewCriteriaRepositoryImpl implements ReviewCriteriaRepository{

	@PersistenceContext
	private  EntityManager entityManager;
	
	@Autowired
	Environment environment;
	
	@Autowired
	ReviewMapper mapper;

	public List<ReviewDto> findAllReviewsWithFilters(SearchCriteria reviewSearchCriteria) throws ReviewNotFoundException  {
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Review> criteriaQuery = criteriaBuilder.createQuery(Review.class);
		Root<Review> reviewRoot = criteriaQuery.from(Review.class);
		
		setOrder(reviewSearchCriteria,criteriaBuilder, criteriaQuery, reviewRoot);
	
		TypedQuery<Review> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(reviewSearchCriteria.getPageNumber() * reviewSearchCriteria.getPageSize());
		typedQuery.setMaxResults(reviewSearchCriteria.getPageSize());
		
		Pageable pageable = getPageable(reviewSearchCriteria);
		long reviewsCount = getReviewsCount(criteriaBuilder);
		
		List<ReviewDto>listOfReviewDtos=new PageImpl<>(mapper.mapReviewListToReviewDtoList(typedQuery.getResultList()),pageable,reviewsCount).getContent();

		if( listOfReviewDtos.isEmpty()) {
			throw new ReviewNotFoundException(HttpStatus.NOT_FOUND.value(),environment.getProperty(ReviewExceptionMessageConstants.REVIEW_LIST_NOT_FOUND_EXCEPTION));
		}
		return listOfReviewDtos;

	}

	private void setOrder(SearchCriteria searchCriteria,CriteriaBuilder criteriaBuilder , CriteriaQuery<Review> criteriaQuery, Root<Review> reviewRoot) {

		if (searchCriteria.getSort().getDirection().equals(org.springframework.data.domain.Sort.Direction.ASC.toString())) {
			criteriaQuery.orderBy(criteriaBuilder.asc(reviewRoot.get(searchCriteria.getSort().getSortBy())));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.desc(reviewRoot.get(searchCriteria.getSort().getSortBy())));
		}
	}
	
	private Pageable getPageable(SearchCriteria reviewSearchCriteria) {
	
		Sort sort= Sort.by(reviewSearchCriteria.getSort().getDirection(), reviewSearchCriteria.getSort().getSortBy());
		return PageRequest.of(reviewSearchCriteria.getPageNumber(), reviewSearchCriteria.getPageSize(), sort);
	}

	private long getReviewsCount(CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Review> countRoot = countQuery.from(Review.class);
		countQuery.select(criteriaBuilder.count(countRoot));
		return entityManager.createQuery(countQuery).getSingleResult();
	}

}
