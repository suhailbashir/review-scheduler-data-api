package com.xbank.demo.graph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbank.demo.graph.model.Criteria;
import com.xbank.demo.graph.model.Review;
import com.xbank.demo.graph.model.ReviewResponseDTO;
import com.xbank.demo.graph.model.Sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewGraphServiceImpl implements ReviewGraphService{

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Environment environment;

	@Value("${review.service.url}")
	private String url;

	@Autowired
	private ObjectMapper objectMapper;

	//@Override
	public List<Review> findReviews(Criteria criteria) throws JsonProcessingException  {
		log.debug("Inside Service class for findReview");
		HttpEntity<String> h = getRequestEntity(getRequest(criteria));
		ResponseEntity<String> reviewDataResponse = this.restTemplate.exchange(url, HttpMethod.POST, h, String.class);

		List<Review> response = getReviewDomianResponse(reviewDataResponse);
		return response;
	}

	public HttpEntity<String> getRequestEntity(String requestPayload) {

		HttpHeaders headers = new HttpHeaders();
		if (headers.get(HttpHeaders.CONTENT_TYPE) == null || headers.get(HttpHeaders.CONTENT_TYPE).isEmpty()) {
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		}
		return new HttpEntity<String>(requestPayload, headers);
	}

	public String getRequest(Criteria criteria) throws JsonProcessingException {
		Sort s = Sort.builder().sortBy(criteria.getSort().getSortBy()).direction(criteria.getSort().getDirection())
				.build();
		Criteria sc = Criteria.builder().pageNumber(criteria.getPageNumber())
				.pageSize(criteria.getPageSize()).sort(s).build();
		return this.objectMapper.writeValueAsString(sc).toString();
	}

	@SuppressWarnings("unchecked")
	public List<Review> getReviewDomianResponse(ResponseEntity< String> reviewDataResponse) throws JsonMappingException, JsonProcessingException  {
		List<Review> response = null;
		if(reviewDataResponse!=null && reviewDataResponse.getBody()!=null) {
			try {
				ReviewResponseDTO reviewResponseDTO =this.objectMapper.readValue(reviewDataResponse.getBody(), ReviewResponseDTO.class);	
			    response = reviewResponseDTO.getReviews();
			}
			catch (Exception e) {
				//throw new ReviewNotFoundException(ErrorCodes.DATA_NOT_FOUND_ERROR.getErrorCode(),ErrorCodes.DATA_NOT_FOUND_ERROR.getErrorMessage());
			log.info(e.getLocalizedMessage());
			}
		};
		return response;
	}

}
