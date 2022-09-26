package org.ait.project.blu.modules.auth.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequestDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
