package org.ait.project.blu.modules.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.ait.project.blu.modules.auth.model.entity.Users;

/**
 * A DTO for the {@link Users} entity
 */
@Data
public class UserResponseDto {
    @JsonProperty("username")
    private final String username;
    @JsonProperty("password")
    private final String password;
    @JsonProperty("first_name")
    private final String firstName;
    @JsonProperty("last_name")
    private final String lastName;
    @JsonProperty("email")
    private final String email;
}