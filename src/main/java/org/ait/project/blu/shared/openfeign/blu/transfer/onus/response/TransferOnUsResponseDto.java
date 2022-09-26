package org.ait.project.blu.shared.openfeign.blu.transfer.onus.response;

import lombok.Data;

@Data
public class TransferOnUsResponseDto {
	private String date;
	private String resourceId;
	private String referenceNumber;
	private String errorMessage;
	private String errorCode;
	private String receiptIdentifier;
	private String savingsId;
}