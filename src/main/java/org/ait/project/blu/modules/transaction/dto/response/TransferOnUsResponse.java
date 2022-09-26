package org.ait.project.blu.modules.transaction.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransferOnUsResponse {
    private String date;
    @JsonProperty("resource_id")
    private String resourceId;
    @JsonProperty("reference_number")
    private String referenceNumber;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("receipt_identifier")
    private String receiptIdentifier;
    @JsonProperty("savings_id")
    private String savingsId;
}
