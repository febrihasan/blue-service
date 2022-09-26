package org.ait.project.blu.shared.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseEnum {
  SUCCESS("BLU-AUTH-0000", "success", HttpStatus.OK),
  CREATE_USER_FAILED("BLU-AUTH-0001", "create.user.failed", HttpStatus.EXPECTATION_FAILED),

  USER_ALREADY_EXISTS("BLU-AUTH-0005", "user.already.exists", HttpStatus.EXPECTATION_FAILED),
  LOGIN_USER_FAILED("BLU-AUTH-0002", "login.user.failed", HttpStatus.UNAUTHORIZED),
  USER_NOT_FOUND("BLU-AUTH-0003", "user.not.found", HttpStatus.NOT_FOUND),
  REFRESH_TOKEN_FAILED("BLU-AUTH-0004", "refresh.token.failed", HttpStatus.BAD_REQUEST),
  TRANSFER_ONUS_FAILED("BLU-TRNX-0001", "transfer.onus.failed", HttpStatus.BAD_REQUEST),
  TRANSFER_OTHER_BANK_FAILED("BLU-TRNX-0002", "transfer.other.bank.failed", HttpStatus.BAD_REQUEST),

  INVALID_PARAM("BLU-AUTH-0099", "invalid.param", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR("BLU-AUTH-9999", "internal.server.error", HttpStatus.INTERNAL_SERVER_ERROR);

  private String responseCode;
  private String responseMessage;
  private HttpStatus httpStatus;

}
