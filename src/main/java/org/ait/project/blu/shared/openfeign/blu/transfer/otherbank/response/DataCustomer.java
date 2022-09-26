package org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataCustomer {
	@JsonProperty("beneficiary_name")
	private String beneficiaryName;
	private int amount;
	@JsonProperty("forwarding_customer_reference_number")
	private String forwardingCustomerReferenceNumber;
	@JsonProperty("time_stamp")
	private String timeStamp;
	@JsonProperty("balance_after")
	private long balanceAfter;
	@JsonProperty("customer_reference_number")
	private String customerReferenceNumber;
	@JsonProperty("destination_bank_name")
	private String destinationBankName;
	@JsonProperty("bank_reference_number")
	private String bankReferenceNumber;
	@JsonProperty("destination_bank_code")
	private String destinationBankCode;
	@JsonProperty("sender_name")
	private String senderName;
	@JsonProperty("beneficiary_account_number")
	private String beneficiaryAccountNumber;
	@JsonProperty("currency_code")
	private String currencyCode;
}