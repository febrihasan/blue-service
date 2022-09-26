package org.ait.project.blu.shared.openfeign.blu.history.response;

import lombok.Data;

@Data
public class PaymentDetailData{
	private String descRow1;
	private int id;
	private String descRow2;
	private String accountNumber;
	private String routingCode;
	private String receiptNumber;
	private PaymentType paymentType;
	private String bankNumber;
	private String checkNumber;
}