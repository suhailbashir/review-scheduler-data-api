package com.xbank.demo.graph.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xbank.demo.graph.model.Criteria;
import com.xbank.demo.graph.model.Review;

public interface ReviewGraphService {
	
	public List<Review> findReviews(Criteria criteria)  throws JsonProcessingException  ;

}
