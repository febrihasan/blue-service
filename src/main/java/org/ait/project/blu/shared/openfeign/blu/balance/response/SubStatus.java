package org.ait.project.blu.shared.openfeign.blu.balance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubStatus{
	private String code;
	private boolean inactive;
	private boolean dormant;
	private boolean escheat;
	@JsonProperty("block_debit")
	private boolean blockDebit;
	private boolean block;
	private int id;
	private boolean none;
	@JsonProperty("block_credit")
	private boolean blockCredit;
	private String value;
}