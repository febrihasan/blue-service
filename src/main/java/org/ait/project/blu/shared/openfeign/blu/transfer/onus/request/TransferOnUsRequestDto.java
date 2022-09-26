package org.ait.project.blu.shared.openfeign.blu.transfer.onus.request;

import lombok.Data;

@Data
public class TransferOnUsRequestDto {
	private String transferDescription;
	private String toClientId;
	private String phoneNumber;
	private String bluRewardsId;
	private String callbackId;
	private String toAccountNo;
	private String accountType;
	private int transferAmount;
	private String channelOpenAccount;
	private String receiptIdentifier;
	private String fromAccountNo;
	private String voucherCode;
}