package org.ait.project.blu.modules.transaction.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.ait.project.blu.modules.transaction.common.PathTransactionApi;
import org.ait.project.blu.modules.transaction.dto.request.TransactionHistoryRequest;
import org.ait.project.blu.modules.transaction.dto.request.TransferOnUsRequest;
import org.ait.project.blu.modules.transaction.dto.response.TransferOnUsResponse;
import org.ait.project.blu.modules.transaction.service.TransactionService;
import org.ait.project.blu.shared.dto.template.ResponseDetail;
import org.ait.project.blu.shared.dto.template.ResponseList;
import org.ait.project.blu.shared.dto.template.ResponseTemplate;
import org.ait.project.blu.shared.openfeign.blu.history.response.TransactionHistoryResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.response.TransferOnUsResponseDto;
import org.ait.project.blu.modules.transaction.dto.request.TransferOtherBankRequest;
import org.ait.project.blu.shared.openfeign.blu.balance.response.BalanceResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.response.TransferOtherBankResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionService {

    private final TransactionService transactionService;

    /**
     * Rest api for get balance
     * @param accountNo with blu account
     * @return data balance
     */
    @GetMapping(PathTransactionApi.BALANCE)
    public ResponseEntity<ResponseTemplate<ResponseList<BalanceResponseDto>>>
    getBalance(@PathVariable String accountNo) {
        return transactionService.getBalance(accountNo);
    }

    /**
     * Rest api for posting transfer on us with (blu account)
     * @param transferOnUs payload transfer on us
     * @return information transaction
     */
    @PostMapping(PathTransactionApi.TRANSFER_ONUS)
    public ResponseEntity<ResponseTemplate<ResponseDetail<TransferOnUsResponse>>>
    transferOnUs(@RequestBody TransferOnUsRequest transferOnUs) {
        return transactionService.transferOnUs(transferOnUs);
    }

    /**
     * Rest api for posting transfer to another bank
     * @param transferOtherBank payload transfer another bank
     * @param accountNo with target account
     * @return information transaction
     */
    @PostMapping(PathTransactionApi.TRANSFER_OTHER_BANK)
    public ResponseEntity<ResponseTemplate<ResponseDetail<TransferOtherBankResponseDto>>>
    transferOtherBank(@RequestBody TransferOtherBankRequest transferOtherBank,
                                                          @PathVariable String accountNo) {
        return transactionService.transferOtherBank(transferOtherBank, accountNo);
    }

    @PostMapping(PathTransactionApi.TRANSACTION_HISTORY)
    public ResponseEntity<ResponseTemplate<ResponseList<TransactionHistoryResponseDto>>>
    transactionHistory(@RequestBody TransactionHistoryRequest transactionHistoryRequest) {
        return transactionService.transactionHistory(transactionHistoryRequest);
    }

}
