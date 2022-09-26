package org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.request;

import lombok.Data;

@Data
public class TransferOtherBankRequestDto {
	private String command;
	private String voucherCode;
	private DataCustomer data;
	private String callbackId;
	private String receiptIdentifier;
	private String bluRewardsId;
	private String channelOpenAccount;
	private String accountType;


}