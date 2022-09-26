package org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransferOtherBankResponseDto {
	private double feeAmount;
	@JsonProperty("response_code")
	private String responseCode;
	private DataCustomer data;
	private double discountAmount;
	@JsonProperty("response_text")
	private String responseText;
	private double feeDiscount;
	private String receiptIdentifier;
	private String command;
}