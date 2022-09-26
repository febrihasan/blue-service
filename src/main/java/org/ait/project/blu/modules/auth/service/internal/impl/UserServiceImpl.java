package org.ait.project.blu.modules.auth.service.internal.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ait.project.blu.modules.auth.dto.request.LoginRequestDto;
import org.ait.project.blu.modules.auth.dto.request.RefreshTokenRequestDto;
import org.ait.project.blu.modules.auth.dto.request.UserRequestDto;
import org.ait.project.blu.modules.auth.dto.response.LoginResponseDto;
import org.ait.project.blu.modules.auth.dto.response.RefreshTokenResponseDto;
import org.ait.project.blu.modules.auth.dto.response.UserResponseDto;
import org.ait.project.blu.modules.auth.model.entity.Users;
import org.ait.project.blu.modules.auth.model.transform.UserTransform;
import org.ait.project.blu.modules.auth.service.delegate.UserDelegate;
import org.ait.project.blu.modules.auth.service.internal.KeycloakAdminService;
import org.ait.project.blu.modules.auth.service.internal.UserService;
import org.ait.project.blu.shared.constant.enums.ResponseEnum;
import org.ait.project.blu.shared.dto.template.ResponseDetail;
import org.ait.project.blu.shared.dto.template.ResponseTemplate;
import org.ait.project.blu.shared.utils.BaseUtils;
import org.ait.project.blu.shared.utils.ResponseHelper;
import org.ait.project.blu.shared.utils.SecureUtils;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**.
 * Class user service implementation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ResponseHelper responseHelper;

    private final UserDelegate userDelegate;

    private final UserTransform userTransform;

    private final KeycloakAdminService keycloakAdminService;

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponseDto>>>
    createUser(UserRequestDto userRequestDto) {

        try {
            Users users = userDelegate.findByUsername(userRequestDto.getUsername());
            if (BaseUtils.isNull(users)) {
                UserRepresentation userRepresentation = keycloakAdminService
                        .createUserKeycloak(userRequestDto);
                users = userTransform.userDtoToUser(userRequestDto);
                users.setUserId(userRepresentation.getId());
                users.setPassword(SecureUtils.encrypted(users.getPassword()));

                return responseHelper
                        .createResponseDetail(ResponseEnum.SUCCESS,
                                userTransform
                                        .userToUserDto(userDelegate.save(users)));
            } else {
                return responseHelper
                        .createResponseDetail(ResponseEnum.USER_ALREADY_EXISTS,
                                userTransform.userFailed(null));
            }
        } catch (Exception e) {
            log.error("Create user failed : {}", e);
        }

        return responseHelper
                .createResponseDetail(ResponseEnum.CREATE_USER_FAILED,
                        userTransform.userFailed(null));
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<LoginResponseDto>>>
    loginUser(LoginRequestDto loginRequestDto) {
        AccessTokenResponse accessTokenResponse = null;
        LoginResponseDto loginResponseDto = null;

        try {
            Keycloak keycloak = keycloakAdminService.loginUserKeycloak(loginRequestDto);
            accessTokenResponse = keycloak.tokenManager().getAccessToken();

            loginResponseDto = new LoginResponseDto();
            loginResponseDto.setUsername(loginRequestDto.getUsername());
            loginResponseDto.setPassword(loginRequestDto.getPassword());
            loginResponseDto.setData(accessTokenResponse);

            return responseHelper
                    .createResponseDetail(ResponseEnum.SUCCESS, loginResponseDto);
        } catch (Exception e) {
            log.error("Login user invalid : {}", e);
        }

        return responseHelper
                .createResponseDetail(ResponseEnum.LOGIN_USER_FAILED, loginResponseDto);
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<AccessToken>>>
    verificationToken(RefreshTokenRequestDto refreshTokenRequest) {
        AccessToken accessToken = null;
        try {
            accessToken = keycloakAdminService.verificationToken(refreshTokenRequest);
            log.info("Token request : {} ", refreshTokenRequest.getRefreshToken());
            log.info("Token from keycloak : {}", accessToken);
        } catch (Exception e) {
            log.error("Validation token invalid {}", e);
        }
        return responseHelper
                .createResponseDetail(ResponseEnum.SUCCESS, accessToken);
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<RefreshTokenResponseDto>>>
    refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
        AccessTokenResponse accessTokenResponse = null;
        RefreshTokenResponseDto refreshTokenResponse = null;
        try {
            accessTokenResponse = keycloakAdminService.refreshTokenKeycloak(refreshTokenRequest);

            refreshTokenResponse = new RefreshTokenResponseDto();
            refreshTokenResponse.setUsername(refreshTokenRequest.getUsername());
            refreshTokenResponse.setPassword(refreshTokenRequest.getPassword());
            refreshTokenResponse.setData(accessTokenResponse);

            return responseHelper
                    .createResponseDetail(ResponseEnum.SUCCESS, refreshTokenResponse);
        } catch (Exception e) {
            log.error("Refresh token failed : {} ", accessTokenResponse);
        }

        return responseHelper
                .createResponseDetail(ResponseEnum.REFRESH_TOKEN_FAILED, refreshTokenResponse);

    }

}
