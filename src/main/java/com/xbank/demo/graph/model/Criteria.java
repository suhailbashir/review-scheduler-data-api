package com.xbank.demo.graph.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Criteria implements Serializable {
int pageNumber;
int pageSize;
Sort sort;

}