package org.ait.project.blu.modules.auth.service.internal.impl;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ait.project.blu.config.keycloak.KeycloakProvider;
import org.ait.project.blu.modules.auth.common.enums.ParamAuthEnum;
import org.ait.project.blu.modules.auth.dto.request.LoginRequestDto;
import org.ait.project.blu.modules.auth.dto.request.RefreshTokenRequestDto;
import org.ait.project.blu.modules.auth.dto.request.UserRequestDto;
import org.ait.project.blu.modules.auth.service.internal.KeycloakAdminService;
import org.ait.project.blu.shared.openfeign.blu.AuthClient;
import org.ait.project.blu.shared.utils.BaseUtils;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.representation.TokenIntrospectionResponse;
import org.keycloak.jose.jws.JWSHeader;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    private final AuthClient authClient;

    private final KeycloakProvider keycloakProvider;

    private Map<String, List<String>> roleMapping = new HashMap<>();

    public UserRepresentation createUserKeycloak(UserRequestDto userRequestDto) {
        this.roleMapping.put(this.clientId, Collections.singletonList(""));

        UserRepresentation userRepresentation = null;
        try {
            UsersResource usersResource = keycloakProvider.getAdminKeycloak().realm(realm).users();
            CredentialRepresentation credentialRepresentation = createPasswordCredentials(userRequestDto.getPassword());

            userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(userRequestDto.getUsername());
            userRepresentation.setFirstName(userRequestDto.getFirstName());
            userRepresentation.setLastName(userRequestDto.getLastName());
            userRepresentation.setEmail(userRequestDto.getEmail());
            userRepresentation.setClientRoles(roleMapping);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(false);

            usersResource.create(userRepresentation);

            List<UserRepresentation> userList = usersResource
                    .search(userRequestDto.getUsername())
                    .stream()
                    .filter(userRep -> userRep.getUsername().equals(userRequestDto.getUsername()))
                    .collect(Collectors.toList());
            userRepresentation = userList.get(0);

            log.info("User with id: " + userRepresentation.getId() + " created");

        } catch (Exception e) {
            log.error("Create user failed : {}", e);
        }

        return userRepresentation;
    }

    public Keycloak loginUserKeycloak(LoginRequestDto loginRequestDto) {
        Keycloak keycloak = null;
        try {
            keycloak = keycloakProvider.newKeycloakBuilderWithPasswordCredentials(
                            loginRequestDto.getUsername(), loginRequestDto.getPassword());
        } catch (Exception e) {
            log.error("Login user invalid : {}", e);
        }

        return keycloak;
    }

    @Override
    public AccessToken verificationToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String token = refreshTokenRequestDto.getRefreshToken();
        AccessToken accessToken = null;

        try {
            // ===  Verify Token : Varian 1  ===
            /* RSATokenVerifier verifier = RSATokenVerifier.create(token);
            PublicKey publicKey = retrivePublicKeyFromCertsEndpoint(verifier.getHeader());

            accessToken = verifier.realmUrl(getRealmUrl())
                    .publicKey(publicKey)
                    .verify()
                    .getToken();
            log.info("access token : {}", accessToken.getName()); */

            // ===  Verify Token : Varian 2  ===
            /* PublicKey publicKey = retrivePublicKeyFromPublicRealmEndpoint();

            accessToken = RSATokenVerifier
                    .verifyToken(token,
                    publicKey, getRealmUrl());
            log.info("access token : {}", accessToken.getName()); */

            // ===  Verify Token : Varian 3  ===
            Map<String, String> verifyToken = new HashMap<>();
            verifyToken.put(ParamAuthEnum.CLIENT_ID.getKey(), clientId);
            verifyToken.put(ParamAuthEnum.CLIENT_SECRET.getKey(), clientSecret);
            verifyToken.put(ParamAuthEnum.TOKEN.getKey(), token);

            /* accessToken = authClient.verificationToken(verifyToken); */

            // get flag active token
            TokenIntrospectionResponse response = authClient.verificationToken(verifyToken);
            log.info("access token : {}", response.getActive());
            if (response.getActive() == Boolean.FALSE) {
                throw new RuntimeException("Token is not active");
            }
        } catch (Exception e) {
            log.error("verification error : [{}]", e.getMessage());
        }

        return accessToken;
    }

    @Override
    public AccessTokenResponse refreshTokenKeycloak(RefreshTokenRequestDto refreshTokenRequest) {
        AccessTokenResponse accessTokenResponse = null;
        try {
            Map<String, String> tokenRequest = new HashMap<>();
            tokenRequest.put(ParamAuthEnum.GRANT_TYPE.getKey(), OAuth2Constants.REFRESH_TOKEN);
            tokenRequest.put(ParamAuthEnum.CLIENT_ID.getKey(), clientId);
            tokenRequest.put(ParamAuthEnum.CLIENT_SECRET.getKey(), clientSecret);
            tokenRequest.put(OAuth2Constants.REFRESH_TOKEN, refreshTokenRequest.getRefreshToken());

            accessTokenResponse = authClient.refreshToken(tokenRequest);
        } catch (Exception e) {
            log.error("Refresh token failed : {}", e);
        }

        return accessTokenResponse;
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(OAuth2Constants.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    private PublicKey retrivePublicKeyFromCertsEndpoint(JWSHeader jwsHeader) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> certInfos = authClient.getPublicKeyFromCerts();
            log.info("Maps : {}", certInfos);

            List<Map<String, Object>> keys = (List<Map<String, Object>>) certInfos.get("keys");
            Map<String, Object> keyInfo = new HashMap<>();
            for (Map<String, Object> key : keys) {
                String kid = (String) key.get("kid");
                if (jwsHeader.getKeyId().equals(kid)) {
                    log.info("kid header : {}, kid map : {}", jwsHeader.getKeyId(), kid);
                    keyInfo = key;
                    break;
                }
            }
            log.info("Key Info : {}", keyInfo);
            if (BaseUtils.isNull(keyInfo)) {
                return null;
            }

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            String modulusBase64 = (String) keyInfo.get("n");
            String exponentBase64 = (String) keyInfo.get("e");

            // see org.keycloak.jose.jwk.JWKBuilder#rs256
            Base64.Decoder urlDecoder = Base64.getUrlDecoder();
            BigInteger modulus = new BigInteger(1, urlDecoder.decode(modulusBase64));
            BigInteger publicExponent = new BigInteger(1, urlDecoder.decode(exponentBase64));

            return keyFactory.generatePublic(new RSAPublicKeySpec(modulus, publicExponent));
        } catch (Exception e) {
            log.error("Retrive public key with certs", e);
        }

        return null;
    }

    private PublicKey retrivePublicKeyFromPublicRealmEndpoint() {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> realInfo = authClient.getPublicKeyFromRealm();

            return toPublicKey((String) realInfo.get("public_key"));
        } catch (Exception e) {
            log.error("Retrive public key with public realm", e);
        }

        return null;
    }

    public String getRealmUrl() {
        return serverUrl + "/realms/" + realm;
    }

    private PublicKey toPublicKey(String publicKeyString) {
        try {
            byte[] publicBytes = Base64.getDecoder().decode(publicKeyString);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Generate public key", e);
            return null;
        }
    }

}