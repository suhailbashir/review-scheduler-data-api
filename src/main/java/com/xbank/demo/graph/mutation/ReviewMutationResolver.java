package com.xbank.demo.graph.mutation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.xbank.demo.graph.model.Criteria;
import com.xbank.demo.graph.model.Review;
import com.xbank.demo.graph.service.ReviewGraphService;

public class ReviewMutationResolver implements GraphQLMutationResolver{

	@Autowired
	ReviewGraphService reviewGraphService;
	
	
	public List<Review> reviews(Criteria j) throws JsonProcessingException {
		List<Review> review = reviewGraphService.findReviews(j);
		return review; 
    }

}
