package org.ait.project.blu.shared.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCollection<T>{

	@JsonProperty("pagination")
	private PaginationConfig paginationConfig;

	@JsonProperty("content")
	private List<T> content;
}