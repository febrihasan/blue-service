package org.ait.project.blu.modules.transaction.service;

import java.util.List;
import org.ait.project.blu.modules.transaction.dto.request.TransactionHistoryRequest;
import org.ait.project.blu.modules.transaction.dto.response.TransferOnUsResponse;
import org.ait.project.blu.shared.dto.template.ResponseDetail;
import org.ait.project.blu.shared.dto.template.ResponseList;
import org.ait.project.blu.shared.dto.template.ResponseTemplate;
import org.ait.project.blu.shared.openfeign.blu.history.response.TransactionHistoryResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.response.TransferOnUsResponseDto;
import org.ait.project.blu.modules.transaction.dto.request.TransferOnUsRequest;
import org.ait.project.blu.modules.transaction.dto.request.TransferOtherBankRequest;
import org.ait.project.blu.shared.openfeign.blu.balance.response.BalanceResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.response.TransferOtherBankResponseDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<ResponseTemplate<ResponseList<BalanceResponseDto>>>
    getBalance(String accountNo);

    ResponseEntity<ResponseTemplate<ResponseDetail<TransferOnUsResponse>>>
    transferOnUs(TransferOnUsRequest transferOnUs);

    ResponseEntity<ResponseTemplate<ResponseDetail<TransferOtherBankResponseDto>>>
    transferOtherBank(TransferOtherBankRequest transferOtherBankRequest, String accountNo);

    ResponseEntity<ResponseTemplate<ResponseList<TransactionHistoryResponseDto>>>
    transactionHistory(TransactionHistoryRequest transactionHistoryRequest);

}
