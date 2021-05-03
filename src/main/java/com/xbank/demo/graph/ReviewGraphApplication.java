package com.xbank.demo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.xbank.demo.graph.model.GraphQLErrorAdapter;
import com.xbank.demo.graph.mutation.ReviewMutationResolver;
import com.xbank.demo.graph.query.ReviewQueryResolver;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@SpringBootApplication
@CrossOrigin("*")
public class ReviewGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewGraphApplication.class, args);
	}
	
	@Bean
    public ReviewMutationResolver mutation() {
        return new ReviewMutationResolver();
    }
	
	
	public ReviewQueryResolver query() {
		return new ReviewQueryResolver();
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

}