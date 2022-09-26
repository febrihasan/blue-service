package org.ait.project.blu.shared.openfeign.blu.history.request;

import java.util.List;
import lombok.Data;

@Data
public class TransactionHistoryRequestDto {
	private String transactionTypeValue;
	private String endDate;
	private List<String> accountNo;
	private String lastId;
	private String pageSize;
	private String startDate;
}