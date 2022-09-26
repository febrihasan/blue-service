package org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataCustomer {
	@JsonProperty("date_time")
	private String dateTime;
	@JsonProperty("customer_reference_number")
	private String customerReferenceNumber;
	@JsonProperty("destination_bank_code")
	private String destinationBankCode;
	@JsonProperty("destination_bank_name")
	private String destinationBankName;
	@JsonProperty("beneficiary_account_number")
	private String beneficiaryAccountNumber;
	@JsonProperty("beneficiary_name")
	private String beneficiaryName;
	@JsonProperty("sender_name")
	private String senderName;
	private String description;
	private int amount;
	@JsonProperty("currency_code")
	private String currencyCode;
}