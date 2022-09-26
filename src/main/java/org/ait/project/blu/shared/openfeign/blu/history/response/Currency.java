package org.ait.project.blu.shared.openfeign.blu.history.response;

import lombok.Data;

@Data
public class Currency{
	private String displayLabel;
	private String code;
	private int decimalPlaces;
	private String nameCode;
	private int inMultiplesOf;
	private String name;
}