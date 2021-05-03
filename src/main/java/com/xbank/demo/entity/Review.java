
package com.xbank.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "REVIEW")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "REVIEW_NAME", nullable = false)
	private String reviewName;

	@Column(name = "DUE_FOR_REVIEW", nullable = false)
	private LocalDate dueForReview;

	@Column(name = "FREQUENCYE", nullable = false)
	private String frequency;

	@Column(name = "REVIEW_TYPE", nullable = false)
	private String reviewType;

	@Column(name = "LAST_UPDATED", nullable = false)
	private LocalDate lastUpdated;

}
