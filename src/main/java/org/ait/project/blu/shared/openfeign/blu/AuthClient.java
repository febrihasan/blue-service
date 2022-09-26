package org.ait.project.blu.shared.openfeign.blu;

import java.util.Map;
import org.ait.project.blu.config.openfeign.OpenFeignConfig;
import org.ait.project.blu.shared.constant.common.PathKeycloakClientAPIs;
import org.keycloak.authorization.client.representation.TokenIntrospectionResponse;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(
    value = "${app.feign.config.auth.name}",
    url = "${app.feign.config.auth.url}",
    path = "${app.feign.config.auth.path}",
    configuration = OpenFeignConfig.class,
    fallback = AuthClientFallback.class
)
public interface AuthClient {

    @PostMapping(value = PathKeycloakClientAPIs.REFRESH_TOKEN,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AccessTokenResponse refreshToken(@RequestBody Map<String, ?> refreshToken);

    @PostMapping(value = PathKeycloakClientAPIs.VERIFICATION_TOKEN,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenIntrospectionResponse verificationToken(@RequestBody Map<String, ?> verifyToken);

    @GetMapping(value = PathKeycloakClientAPIs.PUBLIC_KEY_WITH_REALM)
    Map<String, Object> getPublicKeyFromRealm();

    @GetMapping(value = PathKeycloakClientAPIs.PUBLIC_KEY_WITH_CERTS)
    Map<String, Object> getPublicKeyFromCerts();


}
