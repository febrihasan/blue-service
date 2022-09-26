package org.ait.project.blu.shared.openfeign.blu.balance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BalanceResponseDto {
	@JsonProperty("savings_product_name")
	private String savingsProductName;
	@JsonProperty("short_product_name")
	private String shortProductName;
	@JsonProperty("account_no")
	private String accountNo;
	@JsonProperty("savings_product_id")
	private int savingsProductId;
	private Currency currency;
	@JsonProperty("account_balance")
	private int accountBalance;
	@JsonProperty("deposit_type")
	private DepositType depositType;
	@JsonProperty("sub_status")
	private SubStatus subStatus;
	private Status status;
}