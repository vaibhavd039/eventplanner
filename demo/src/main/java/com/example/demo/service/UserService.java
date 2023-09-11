package com.example.demo.service;

import com.example.demo.builder.UserBuilder;
import com.example.demo.domain.UserEntity;
import com.example.demo.dto.UserCreationRequestDTO;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserCreationRequestDTO createUser(UserCreationRequestDTO requestDTO) {
    UserEntity userEntity = UserBuilder.fromUserRequestDto(requestDTO);
    userRepository.save(userEntity);
    return requestDTO;
  }

  public UserCreationRequestDTO getUserById(Long userId) {
    UserEntity user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    return UserBuilder.fromEntity(user);
  }


  public UserEntity getUserEntityById(Long userId) {
    return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
  }

  public Set<UserEntity> getUserEntityByIdsIN(List<Long> ids) {
    return userRepository.findUserEntitiesByIdIn(ids);
  }
}
