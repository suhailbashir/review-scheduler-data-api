package com.xbank.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbank.demo.ObjectBuilderUtility;
import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.dto.SearchCriteria;
import com.xbank.demo.dto.Sort;
import com.xbank.demo.exception.DuplicateReviewException;
import com.xbank.demo.exception.ReviewNotFoundException;
import com.xbank.demo.mappers.ReviewMapper;
import com.xbank.demo.service.ReviewDataService;

@WebMvcTest(value = ReviewDataController.class)
@ActiveProfiles("default")
@OverrideAutoConfiguration(enabled = true)
 class ReviewResourceTests {

	
	@MockBean
	ReviewDataService reviewDataService;
	
	@MockBean
	ReviewMapper reviewMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	void saveReviewTest() throws Exception {
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();
		
		when(reviewDataService.saveReview(reviewDto)).thenReturn(reviewDto);
		
		String url="/review-scheduler-data-api/review";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(reviewDto));
	
		mockMvc.perform(builder).andExpect(status().isCreated());		
	}
	
	
	@Test
	void saveReviewTestFail() throws Exception {
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();
		
		when(reviewDataService.saveReview(reviewDto)).thenThrow(DuplicateReviewException.class);
		
		String url="/review-scheduler-data-api/review";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(null));
	
		mockMvc.perform(builder).andExpect(status().isBadRequest());		
	}
	
	@Test
	void saveReviewTestFail2() throws Exception {
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();
		
		when(reviewDataService.saveReview(Mockito.any())).thenThrow(new DuplicateReviewException(400,"Not Found"));
		
		String url="/review-scheduler-data-api/review";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(reviewDto));

		mockMvc.perform(builder).andExpect(status().isBadRequest());		
	}
	
	
	@Test
	void findAllReviewsWithFiltersTest() throws Exception {
		List<ReviewDto>listOfreviewDtos=ObjectBuilderUtility.createReviewDtoList();
		String url="/review-scheduler-data-api/reviews";

		Sort sort = Sort.builder().direction("ASC").sortBy("reviewName").build();
		SearchCriteria searchCriteria = SearchCriteria.builder().pageNumber(0).pageSize(1).sort(sort).build();
		when(reviewDataService.findAllReviewsWithFilters(Mockito.any())).thenReturn(listOfreviewDtos);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(searchCriteria));
		mockMvc.perform(builder).andExpect(status().is2xxSuccessful());		
	}
	

	@Test
	void findAllReviewsTest() throws Exception {
		List<ReviewDto>listOfreviewDtos=ObjectBuilderUtility.createReviewDtoList();
		
		when(reviewDataService.findAllReviews()).thenReturn(listOfreviewDtos);
		
		String url="/review-scheduler-data-api/review";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(listOfreviewDtos));
	
		mockMvc.perform(builder).andExpect(status().isOk());		
		
	}
	
	@Test
	void findReviewByIdTest() throws Exception {
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();
		
		when(reviewDataService.findReviewById(Mockito.any())).thenReturn(reviewDto);
		
		String url="/review-scheduler-data-api/review/{id}";
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url,1L)
																		  .contentType(MediaType.APPLICATION_JSON_VALUE)
																		  .accept(MediaType.APPLICATION_JSON)
																		  .characterEncoding("UTF-8");
	
		
		// mockMvc.perform(get("/review-scheduler-data-api/review/{id}", 1L)).andExpect(status().isOk()).andReturn();
		mockMvc.perform(builder).andExpect(status().isOk());		
	}
	
	
	@Test
	void findReviewByIdTestFail() throws Exception {
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();
		
		when(reviewDataService.findReviewById(Mockito.any())).thenThrow(new ReviewNotFoundException(404,"Not Found"));
		
		String url="/review-scheduler-data-api/review/{id}";
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url,1L)
																		  .contentType(MediaType.APPLICATION_JSON_VALUE)
																		  .accept(MediaType.APPLICATION_JSON)
																		  .characterEncoding("UTF-8");
	
		
		mockMvc.perform(builder).andExpect(status().is4xxClientError());			
	}
	
	
	@Test
	void deleteReviewTest() throws Exception {
	
		List<ReviewDto>listOfreviewDtos=ObjectBuilderUtility.createReviewDtoList();
		when(reviewDataService.deleteReviewById(Mockito.any())).thenReturn(listOfreviewDtos);
		
		String url="/review-scheduler-data-api/review/{id}";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(url,1L)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8");
	
		mockMvc.perform(builder).andExpect(status().isOk());		
	}

	@Test
	void updateReviewTest() throws Exception {
		ReviewDto reviewDto = ObjectBuilderUtility.createReviewDto();
		
		when(reviewDataService.updateReview(Mockito.any(),Mockito.any())).thenReturn(reviewDto);
		
		String url="/review-scheduler-data-api/review/{id}";
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(url,1L)
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(reviewDto));
	
		mockMvc.perform(builder).andExpect(status().isOk());		
	}

	
}