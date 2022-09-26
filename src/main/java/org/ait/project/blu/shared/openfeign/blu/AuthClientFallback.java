package org.ait.project.blu.shared.openfeign.blu;

import java.util.Map;
import org.keycloak.authorization.client.representation.TokenIntrospectionResponse;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.stereotype.Component;

@Component
public class AuthClientFallback implements AuthClient {

  /**.
   * When Openfeign Call failed, then do this
   */

    @Override
    public AccessTokenResponse refreshToken(Map<String, ?> tokenRequest) {
        throw new NoFallbackAvailableException("Refresh token has failed",
                new RuntimeException());
    }

    @Override
    public TokenIntrospectionResponse verificationToken(Map<String, ?> tokenRequest) {
        throw new NoFallbackAvailableException("Verification token invalid / non-active",
                new RuntimeException());
    }

    @Override
    public Map<String, Object> getPublicKeyFromRealm() {
        throw new NoFallbackAvailableException("Public Key with realm is null",
                new RuntimeException());
    }

    @Override
    public Map<String, Object> getPublicKeyFromCerts() {
        throw new NoFallbackAvailableException("Public Key with certs is null",
                new RuntimeException());
    }
}
