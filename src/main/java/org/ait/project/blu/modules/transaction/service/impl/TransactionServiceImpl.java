package org.ait.project.blu.modules.transaction.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ait.project.blu.modules.transaction.dto.request.TransactionHistoryRequest;
import org.ait.project.blu.modules.transaction.dto.response.TransferOnUsResponse;
import org.ait.project.blu.modules.transaction.transform.TransactionTransform;
import org.ait.project.blu.shared.constant.enums.ResponseEnum;
import org.ait.project.blu.shared.dto.template.ResponseDetail;
import org.ait.project.blu.shared.dto.template.ResponseList;
import org.ait.project.blu.shared.dto.template.ResponseTemplate;
import org.ait.project.blu.shared.openfeign.blu.TransactionClient;
import org.ait.project.blu.shared.openfeign.blu.history.response.TransactionHistoryResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.response.TransferOnUsResponseDto;
import org.ait.project.blu.shared.utils.ResponseHelper;
import org.ait.project.blu.modules.transaction.dto.request.TransferOnUsRequest;
import org.ait.project.blu.modules.transaction.dto.request.TransferOtherBankRequest;
import org.ait.project.blu.modules.transaction.service.TransactionService;
import org.ait.project.blu.shared.constant.enums.HeadersEnum;
import org.ait.project.blu.shared.openfeign.blu.balance.response.BalanceResponseDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.otherbank.response.TransferOtherBankResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    /**.
     * Get function ResponseHelper
     */
    private final ResponseHelper responseHelper;

    private final TransactionClient client;

    private final TransactionTransform transactionTransform;

    @Override
    public ResponseEntity<ResponseTemplate<ResponseList<BalanceResponseDto>>>
    getBalance(String accountNo) {

        /*
         * Call feign client to posting transfer on us
         */
        List<BalanceResponseDto> result = client.getBalance(accountNo);

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS, null,
                result);
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<TransferOnUsResponse>>>
    transferOnUs(TransferOnUsRequest transferOnUs) {

        /*
         * Set request headers for transfer on us with blu account
         */
        Map<String, String> headers = new HashMap<>();
        headers.put(HeadersEnum.X_API_KEY.getKey(), "abcdefghij0123456789");
        headers.put(HeadersEnum.CLIENT_ID.getKey(), "blu-digital");
        headers.put(HeadersEnum.COMPANY_CODE.getKey(), "099-099-099");

        /*
         * Call feign client for posting transfer on us with blu account
         */

        TransferOnUsResponseDto result = null;
        try{
            result = client.transferOnUs(headers, transactionTransform.copyFromFrontEnd(transferOnUs));
            return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, transactionTransform.copyResultFromBackEnd(result));
        } catch (Exception e) {
            log.error("Handle Error : {} ", e.getMessage());
            return responseHelper.createResponseDetail(ResponseEnum.TRANSFER_ONUS_FAILED, transactionTransform.copyResultFromBackEnd(result));
        }
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<TransferOtherBankResponseDto>>>
    transferOtherBank(TransferOtherBankRequest transferOtherBank,
                                                          String accountNo) {

        /*
         * Set request headers for transfer to another bank
         */
        Map<String, String> headers = new HashMap<>();
        headers.put(HeadersEnum.X_API_KEY.getKey(), "abcdefghij0123456789");
        headers.put(HeadersEnum.CLIENT_ID.getKey(), "blu-digital");
        headers.put(HeadersEnum.COMPANY_CODE.getKey(), "099-099-099");
        headers.put(HeadersEnum.PAYMENT_TYPE_ID.getKey(), "1");
        headers.put(HeadersEnum.PAYMENT_TYPE_NAME.getKey(), "transfer-other-bank");

        /*
         * Call feign client for posting transfer to another bank
         */
        TransferOtherBankResponseDto result = client.transferOtherBank(headers, transferOtherBank, accountNo);

        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, result);
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseList<TransactionHistoryResponseDto>>>
    transactionHistory(TransactionHistoryRequest transactionHistoryRequest) {

        /*
         * Set request headers for transaction history
         */
        Map<String, String> headers = new HashMap<>();
        headers.put(HeadersEnum.X_API_KEY.getKey(), "abcdefghij0123456789");
        headers.put(HeadersEnum.ACCOUNT_TYPE.getKey(), "bluAccount");

        /*
         * Call feign client to posting transfer on us
         */
        List<TransactionHistoryResponseDto> result = client.transactionHistory(headers, transactionHistoryRequest);

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS, null,
                result);
    }
}
