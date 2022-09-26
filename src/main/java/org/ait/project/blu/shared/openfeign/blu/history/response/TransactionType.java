package org.ait.project.blu.shared.openfeign.blu.history.response;

import lombok.Data;

@Data
public class TransactionType{
	private boolean feeDeduction;
	private boolean overdraftFee;
	private String code;
	private boolean amountRelease;
	private boolean interestPosting;
	private boolean approveTransfer;
	private boolean withdrawal;
	private boolean withdrawTransfer;
	private boolean rejectTransfer;
	private boolean escheat;
	private boolean initiateTransfer;
	private boolean dividendPayout;
	private boolean writtenoff;
	private boolean deposit;
	private int id;
	private boolean withholdTax;
	private boolean amountHold;
	private String value;
	private boolean overdraftInterest;
}