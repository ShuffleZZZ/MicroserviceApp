package com.shufflezzz.Connector.models.dto;

public class UpdateUserDto {

    private Long userId;
    private UserDto userDto;

    public UpdateUserDto(Long userId, UserDto userDto) {
        this.userId = userId;
        this.userDto = userDto;
    }

    public UpdateUserDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
