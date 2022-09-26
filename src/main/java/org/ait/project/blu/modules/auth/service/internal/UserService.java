package org.ait.project.blu.modules.auth.service.internal;

import org.ait.project.blu.modules.auth.dto.request.LoginRequestDto;
import org.ait.project.blu.modules.auth.dto.request.RefreshTokenRequestDto;
import org.ait.project.blu.modules.auth.dto.request.UserRequestDto;
import org.ait.project.blu.modules.auth.dto.response.LoginResponseDto;
import org.ait.project.blu.modules.auth.dto.response.RefreshTokenResponseDto;
import org.ait.project.blu.modules.auth.dto.response.UserResponseDto;
import org.ait.project.blu.shared.dto.template.ResponseDetail;
import org.ait.project.blu.shared.dto.template.ResponseTemplate;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ResponseTemplate<ResponseDetail<UserResponseDto>>>
    createUser(UserRequestDto userRequest);

    ResponseEntity<ResponseTemplate<ResponseDetail<LoginResponseDto>>>
    loginUser(LoginRequestDto loginRequest);

    ResponseEntity<ResponseTemplate<ResponseDetail<AccessToken>>>
    verificationToken(RefreshTokenRequestDto refreshTokenRequest);

    ResponseEntity<ResponseTemplate<ResponseDetail<RefreshTokenResponseDto>>>
    refreshToken(RefreshTokenRequestDto refreshTokenRequest);

}
