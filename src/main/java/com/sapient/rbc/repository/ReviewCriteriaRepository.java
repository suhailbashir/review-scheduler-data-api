package com.sapient.rbc.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.dto.SearchCriteria;
import com.sapient.rbc.entity.Review;
import com.sapient.rbc.mappers.ReviewMapper;

@Repository
public class ReviewCriteriaRepository {

	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;

	public ReviewCriteriaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	@Autowired
	ReviewMapper mapper;

	public Page<ReviewDto> findAllReviewsWithFilters(SearchCriteria reviewSearchCriteria) {
		CriteriaQuery<Review> criteriaQuery = criteriaBuilder.createQuery(Review.class);
		Root<Review> reviewRoot = criteriaQuery.from(Review.class);
	
		setOrder(reviewSearchCriteria, criteriaQuery, reviewRoot);

		TypedQuery<Review> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(reviewSearchCriteria.getPageNumber() * reviewSearchCriteria.getPageSize());
		typedQuery.setMaxResults(reviewSearchCriteria.getPageSize());

		Pageable pageable = getPageable(reviewSearchCriteria);

		long reviewsCount = getReviewsCount();
		
		return new PageImpl<>(mapper.mapReviewListToReviewDtoList(typedQuery.getResultList()), pageable, reviewsCount);
	}

	

	private void setOrder(SearchCriteria searchCriteria, CriteriaQuery<Review> criteriaQuery, Root<Review> reviewRoot) {

		if (searchCriteria.getSort().getDirection().getDirectionCode().equals(Sort.Direction.ASC.toString())) {
			criteriaQuery.orderBy(criteriaBuilder.asc(reviewRoot.get(searchCriteria.getSort().getSortBy().getSortBy())));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.desc(reviewRoot.get(searchCriteria.getSort().getSortBy().getSortBy())));
		}
	}

	private Pageable getPageable(SearchCriteria reviewSearchCriteria) {
		Sort sort = Sort.by(reviewSearchCriteria.getSort().getDirection().getDirectionCode(), reviewSearchCriteria.getSort().getSortBy().getSortBy());
		return PageRequest.of(reviewSearchCriteria.getPageNumber(), reviewSearchCriteria.getPageSize(), sort);
	}

	private long getReviewsCount() {
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Review> countRoot = countQuery.from(Review.class);
		countQuery.select(criteriaBuilder.count(countRoot));
		return entityManager.createQuery(countQuery).getSingleResult();
	}

}
