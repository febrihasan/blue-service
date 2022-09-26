package org.ait.project.blu.modules.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ait.project.blu.modules.auth.common.PathAuthApi;
import org.ait.project.blu.modules.auth.dto.request.LoginRequestDto;
import org.ait.project.blu.modules.auth.dto.request.RefreshTokenRequestDto;
import org.ait.project.blu.modules.auth.dto.request.UserRequestDto;
import org.ait.project.blu.modules.auth.dto.response.LoginResponseDto;
import org.ait.project.blu.modules.auth.dto.response.RefreshTokenResponseDto;
import org.ait.project.blu.modules.auth.dto.response.UserResponseDto;
import org.ait.project.blu.modules.auth.service.internal.UserService;
import org.ait.project.blu.shared.dto.template.ResponseDetail;
import org.ait.project.blu.shared.dto.template.ResponseTemplate;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**.
 * Authentication controller for access user
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(PathAuthApi.CREATE_USER)
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponseDto>>>
    createUser(@RequestBody UserRequestDto userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping(PathAuthApi.LOGIN_USER)
    public ResponseEntity<ResponseTemplate<ResponseDetail<LoginResponseDto>>>
    loginUser(@RequestBody LoginRequestDto loginRequest) {
        return userService.loginUser(loginRequest);
    }

    @PostMapping(PathAuthApi.VERIFICATION_TOKEN)
    public ResponseEntity<ResponseTemplate<ResponseDetail<AccessToken>>>
    verificationToken(@RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        return userService.verificationToken(refreshTokenRequest);
    }

    @PostMapping(PathAuthApi.REFRESH_TOKEN)
    public ResponseEntity<ResponseTemplate<ResponseDetail<RefreshTokenResponseDto>>>
    refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        return userService.refreshToken(refreshTokenRequest);
    }

}
