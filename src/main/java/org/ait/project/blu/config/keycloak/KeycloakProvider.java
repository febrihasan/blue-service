package org.ait.project.blu.config.keycloak;

import lombok.Getter;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KeycloakProvider {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${master.keycloak.realm}")
    private String realmMaster;

    @Value("${master.keycloak.client-id}")
    private String clientIdMaster;

    @Value("${master.keycloak.username}")
    private String username;

    @Value("${master.keycloak.password}")
    private String password;

    private static Keycloak keycloak = null;

    public KeycloakProvider() { /* constructor */ }

    public Keycloak getAdminKeycloak() {
          return KeycloakBuilder.builder()
                  .realm(realmMaster)
                  .serverUrl(serverUrl)
                  .clientId(clientIdMaster)
                  .username(username)
                  .password(password)
                  .grantType(OAuth2Constants.PASSWORD)
                  .resteasyClient(
                          new ResteasyClientBuilder()
                                  .connectionPoolSize(10).build())
                  .build();
    }

    public Keycloak newKeycloakBuilderWithCredentials(String clientId, String clientSecret) {
        return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverUrl)
                .clientId(clientId)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientSecret(clientSecret)
                .build();
    }

    public Keycloak newKeycloakBuilderWithPasswordCredentials
            (String username, String password) {
        return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverUrl)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(password)
                .build();
    }

}
