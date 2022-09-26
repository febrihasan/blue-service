package org.ait.project.blu.modules.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.keycloak.representations.AccessTokenResponse;

@Data
public class RefreshTokenResponseDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("data")
    private AccessTokenResponse data;

}
