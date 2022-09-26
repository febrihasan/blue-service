package org.ait.project.blu.shared.openfeign.blu.history.response;

import java.util.List;
import lombok.Data;

@Data
public class Transfer{
	private String transferDescription;
	private String savingsAccount;
	private String clientName;
	private int transferAmount;
	private Currency currency;
	private int id;
	private List<Integer> transferDate;
	private boolean reversed;
}