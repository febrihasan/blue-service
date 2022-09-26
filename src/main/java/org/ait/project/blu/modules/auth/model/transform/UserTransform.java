package org.ait.project.blu.modules.auth.model.transform;

import org.ait.project.blu.modules.auth.dto.response.LoginResponseDto;
import org.ait.project.blu.modules.auth.dto.response.UserResponseDto;
import org.ait.project.blu.modules.auth.model.entity.Users;
import org.ait.project.blu.modules.auth.dto.request.UserRequestDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserTransform {
    Users userDtoToUser(UserRequestDto userRequestDto);

    UserResponseDto userToUserDto(Users users);

    UserResponseDto userFailed(UserRequestDto userRequestDto);

    LoginResponseDto getAccessToken(LoginResponseDto loginResponseDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Users updateUserFromUserDto(UserRequestDto userRequestDto, @MappingTarget Users users);
}
