package com.xbank.demo.graph.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.xbank.demo.graph.model.Criteria;
import com.xbank.demo.graph.model.Review;
import com.xbank.demo.graph.service.ReviewGraphService;

@Component
public class ReviewQueryResolver implements GraphQLQueryResolver {

	@Autowired
	ReviewGraphService reviewGraphService;


	public List<Review> reviewlist(Criteria j) throws JsonProcessingException {
		List<Review> review = reviewGraphService.findReviews(j);
		return review; 
    }
	
}
