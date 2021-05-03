package com.xbank.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xbank.demo.entity.Review;
public interface ReviewRepository extends JpaRepository<Review, Long>,ReviewCriteriaRepository {

}
