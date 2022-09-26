package org.ait.project.blu.shared.openfeign.blu.history.response;

import java.util.List;
import lombok.Data;

@Data
public class TransactionHistoryResponseDto {
	private String accountNo;
	private int lastId;
	private List<TransactionHistoryItem> transactionHistory;
}