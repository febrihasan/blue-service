package org.ait.project.blu.modules.auth.service.internal;

import org.ait.project.blu.modules.auth.dto.request.LoginRequestDto;
import org.ait.project.blu.modules.auth.dto.request.RefreshTokenRequestDto;
import org.ait.project.blu.modules.auth.dto.request.UserRequestDto;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakAdminService {

    UserRepresentation createUserKeycloak(UserRequestDto userRequestDto);

    Keycloak loginUserKeycloak(LoginRequestDto loginRequestDto);

    AccessToken verificationToken(RefreshTokenRequestDto refreshTokenRequestDto);

    AccessTokenResponse refreshTokenKeycloak(RefreshTokenRequestDto refreshTokenRequestDto);

}
