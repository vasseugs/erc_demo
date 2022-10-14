package com.example.erc_demo.service;

import com.example.erc_demo.entity.UserEntity;
import com.example.erc_demo.exception.ServerResponseException;
import com.example.erc_demo.model.UserRegisterDto;
import com.example.erc_demo.repository.UserRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public void registerNewUser(UserRegisterDto userRegisterDto) {
    prepareNewUser(userRegisterDto);
    validateNewUser(userRegisterDto);
    saveNewUser(userRegisterDto.toEntity());
  }

  private void saveNewUser(UserEntity userEntity) {
    userEntity.setCreatedAt(Timestamp.from(Instant.now()));
    userRepository.save(userEntity);
  }

  private void checkDoubledPassword(String password, String doubledPassword) {
    if (!Objects.equals(password, doubledPassword)) {
      throw new ServerResponseException("Passwords don't match");
    }
  }

  private void checkIfLoginExists(String login) {
    var userWithSameLogin = userRepository.findByLogin(login);
    if (userWithSameLogin.isPresent()) {
      throw new ServerResponseException("User with this login already exists");
    }
  }

  /**
   * Подготавливает данные, заполненные пользователем, к валидации
   */
  private void prepareNewUser(UserRegisterDto userRegisterDto) {
    userRegisterDto.setLogin(userRegisterDto.getLogin().trim());
    userRegisterDto.setPassword(userRegisterDto.getPassword().trim());
    userRegisterDto.setDoubledPassword(userRegisterDto.getDoubledPassword().trim());
    userRegisterDto.setFirstName(userRegisterDto.getFirstName().trim());
    userRegisterDto.setLastName(userRegisterDto.getLastName().trim());
  }

  private void validateNewUser(UserRegisterDto userRegisterDto) {
    checkIfLoginExists(userRegisterDto.getLogin());
    checkDoubledPassword(userRegisterDto.getPassword(), userRegisterDto.getDoubledPassword());
  }
}
