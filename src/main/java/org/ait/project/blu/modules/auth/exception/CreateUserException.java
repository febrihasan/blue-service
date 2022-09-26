package org.ait.project.blu.modules.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.ait.project.blu.config.exception.ModuleException;
import org.ait.project.blu.shared.constant.enums.ResponseEnum;

@Slf4j



public class CreateUserException extends ModuleException {

  public CreateUserException() {
    super(ResponseEnum.CREATE_USER_FAILED);
    log.error("Create user failed");
  }
}
