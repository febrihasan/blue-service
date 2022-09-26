package org.ait.project.blu.shared.openfeign.blu.balance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Status{
	@JsonProperty("transfer_on_hold")
	private boolean transferOnHold;
	private String code;
	private boolean rejected;
	@JsonProperty("transfer_in_progress")
	private boolean transferInProgress;
	@JsonProperty("submitted_and_pending_approval")
	private boolean submittedAndPendingApproval;
	private boolean active;
	private boolean approved;
	@JsonProperty("premature_closed")
	private boolean prematureClosed;
	private boolean closed;
	private boolean matured;
	private int id;
	@JsonProperty("withdrawn_by_applicant")
	private boolean withdrawnByApplicant;
	private String value;
}