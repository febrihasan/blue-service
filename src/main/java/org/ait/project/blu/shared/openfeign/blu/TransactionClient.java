package org.ait.project.blu.shared.openfeign.blu;

import java.util.List;
import java.util.Map;
import org.ait.project.blu.modules.transaction.dto.request.TransactionHistoryRequest;
import org.ait.project.blu.modules.transaction.dto.request.TransferOtherBankRequest;
import org.ait.project.blu.shared.constant.common.PathBluClientAPIs;
import org.ait.project.blu.shared.openfeign.blu.balance.response.BalanceResponseDto;
import org.ait.project.blu.shared.openfeign.blu.history.response.TransactionHistoryResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.request.TransferOnUsRequestDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.response.TransferOnUsResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.response.TransferOtherBankResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    value = "${app.feign.config.transaction.name}",
    url = "${app.feign.config.transaction.url}",
    path = "${app.feign.config.transaction.path}",
    fallback = TransactionClientFallback.class
)
public interface TransactionClient {

    @GetMapping(value = PathBluClientAPIs.BALANCE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BalanceResponseDto> getBalance(@PathVariable String accountNo);

    @PostMapping(value = PathBluClientAPIs.TRANSFER_ONUS, consumes = MediaType.APPLICATION_JSON_VALUE)
    TransferOnUsResponseDto transferOnUs(@RequestHeader Map<String, String> headers,
                                                   @RequestBody TransferOnUsRequestDto transferOnUs);

    @PostMapping(value = PathBluClientAPIs.TRANSFER_OTHER_BANK, consumes = MediaType.APPLICATION_JSON_VALUE)
    TransferOtherBankResponseDto transferOtherBank(@RequestHeader Map<String, String> headers,
                                                             @RequestBody TransferOtherBankRequest transferOtherBank,
                                                             @PathVariable String accountNo);

    @PostMapping(value = PathBluClientAPIs.TRANSACTION_HISTORY, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<TransactionHistoryResponseDto> transactionHistory(@RequestHeader Map<String, String> headers,
                                                           @RequestBody TransactionHistoryRequest transactionHistoryRequest);

}
