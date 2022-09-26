package org.ait.project.blu.modules.transaction.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransferOnUsRequest {
    @JsonProperty("transfer_description")
    private String transferDescription;
    @JsonProperty("to_client_id")
    private String toClientId;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("blu_rewards_id")
    private String bluRewardsId;
    @JsonProperty("callback_id")
    private String callbackId;
    @JsonProperty("to_account_no")
    private String toAccountNo;
    @JsonProperty("account_type")
    private String accountType;
    @JsonProperty("transfer_amount")
    private int transferAmount;
    @JsonProperty("channel_open_account")
    private String channelOpenAccount;
    @JsonProperty("receipt_identifier")
    private String receiptIdentifier;
    @JsonProperty("from_account_no")
    private String fromAccountNo;
    @JsonProperty("voucher_code")
    private String voucherCode;
}