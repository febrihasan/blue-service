package org.ait.project.blu.modules.auth.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParamAuthEnum {

    GRANT_TYPE("grant_type"),
    CLIENT_ID("client_id"),
    CLIENT_SECRET("client_secret"),

    TOKEN("token");

    private String key;

}
