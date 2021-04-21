package com.sapient.rbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.rbc.entity.Review;
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
