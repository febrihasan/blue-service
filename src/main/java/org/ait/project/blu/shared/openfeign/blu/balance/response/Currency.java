package org.ait.project.blu.shared.openfeign.blu.balance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency{
	@JsonProperty("display_label")
	private String displayLabel;
	private String code;
	@JsonProperty("decimal_places")
	private int decimalPlaces;
	@JsonProperty("name_code")
	private String nameCode;
	@JsonProperty("in_multiples_of")
	private int inMultiplesOf;
	private String name;
}