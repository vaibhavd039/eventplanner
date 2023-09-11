package com.example.demo.builder;

import com.example.demo.domain.UserEntity;
import com.example.demo.dto.UserCreationRequestDTO;

public final class UserBuilder {

  private UserBuilder() {
  }

  public static UserEntity fromUserRequestDto(UserCreationRequestDTO userCreationRequestDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(userCreationRequestDTO.getName());
    return userEntity;
  }

  public static UserCreationRequestDTO fromEntity(UserEntity entity) {
    return UserCreationRequestDTO.builder().name(entity.getUsername()).build();
  }

}
