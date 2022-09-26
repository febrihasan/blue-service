package org.ait.project.blu.shared.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HeadersEnum {

    X_API_KEY("X-API-Key"),
    CLIENT_ID("clientId"),
    COMPANY_CODE ("companyCode"),
    PAYMENT_TYPE_ID("paymentTypeId"),
    PAYMENT_TYPE_NAME("paymentTypeName"),
    ACCOUNT_TYPE("accountType");

    private String key;

}
