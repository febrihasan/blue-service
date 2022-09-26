package org.ait.project.blu.shared.openfeign.blu;

import java.util.List;
import java.util.Map;
import org.ait.project.blu.modules.transaction.dto.request.TransactionHistoryRequest;
import org.ait.project.blu.modules.transaction.dto.request.TransferOtherBankRequest;
import org.ait.project.blu.shared.openfeign.blu.balance.response.BalanceResponseDto;
import org.ait.project.blu.shared.openfeign.blu.history.response.TransactionHistoryResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.request.TransferOnUsRequestDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.response.TransferOnUsResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.response.TransferOtherBankResponseDto;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.stereotype.Component;

@Component
public class TransactionClientFallback implements TransactionClient {

  /**.
   * When Openfeign Call failed, then do this
   */

    @Override
    public List<BalanceResponseDto> getBalance(String accountNo) {
        throw new NoFallbackAvailableException("Data not found",
                new RuntimeException());
    }

    @Override
    public TransferOnUsResponseDto transferOnUs(Map<String, String> headers,
                                                          TransferOnUsRequestDto transferOnUs) {
        throw new NoFallbackAvailableException("Transaction transfer on us was failed",
                new RuntimeException());
    }

    @Override
    public TransferOtherBankResponseDto transferOtherBank(Map<String, String> headers,
                                                                    TransferOtherBankRequest transferOtherBank,
                                                                    String accountNo) {
        throw new NoFallbackAvailableException("Transaction transfer to another bank was failed",
                new RuntimeException());
    }

    @Override
    public List<TransactionHistoryResponseDto> transactionHistory(Map<String, String> headers, TransactionHistoryRequest transactionHistoryRequest) {
        throw new NoFallbackAvailableException("Data not found", new RuntimeException());
    }
}
