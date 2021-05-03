package com.xbank.demo.graph.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class GraphQLErrorAdapter  implements GraphQLError {

    private GraphQLError error;

    public GraphQLErrorAdapter(GraphQLError error) {
        this.error = error;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return error.getExtensions();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return error.getLocations();
    }

    @Override
    public ErrorType getErrorType() {
        return error.getErrorType();
    }

    @Override
    public List<Object> getPath() {
        return error.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return error.toSpecification();
    }

    @Override
    public String getMessage() {
    	ExceptionWhileDataFetching ee= (ExceptionWhileDataFetching)(error);
		HttpClientErrorException o= (HttpClientErrorException)(ee.getException());
		ReviewBaseResponse reviewResponseDTO = ReviewBaseResponse.builder().build();
		o.getResponseBodyAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			 reviewResponseDTO =objectMapper.readValue(o.getResponseBodyAsString(), ReviewBaseResponse.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
        return (error instanceof ExceptionWhileDataFetching) ? reviewResponseDTO.getErrorDetails().get(0).toString() : error.getMessage();
    }
}