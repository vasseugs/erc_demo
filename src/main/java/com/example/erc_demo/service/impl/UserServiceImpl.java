package com.example.erc_demo.service.impl;

import com.example.erc_demo.entity.UserEntity;
import com.example.erc_demo.exception.ServerResponseException;
import com.example.erc_demo.model.UserAuthDto;
import com.example.erc_demo.model.UserRegisterDto;
import com.example.erc_demo.repository.UserRepository;
import com.example.erc_demo.service.UserService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public void authorizeUser(UserAuthDto userAuthDto) {
    var userOpt = userRepository.findByLogin(userAuthDto.getLogin());

    if (userOpt.isEmpty()) {
      throw new ServerResponseException("User with this login doesn't exist");
    }

    var hashedInputPassword = DigestUtils.sha256Hex(userAuthDto.getPassword());
    if (!Objects.equals(hashedInputPassword, userOpt.get().getPassword())) {
      throw new ServerResponseException("Incorrect password");
    }
  }

  public void registerNewUser(UserRegisterDto userRegisterDto) {
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

  private void checkUserExists(String login) {
    var userWithSameLogin = userRepository.findByLogin(login);
    if (userWithSameLogin.isPresent()) {
      throw new ServerResponseException("User with this login already exists");
    }
  }

  public void checkUserExists(Long id) {
    var userExistsById = userRepository.existsById(id);
    if (!userExistsById) {
      throw new ServerResponseException("User with this id doesn't exist");
    }
  }

  private void validateNewUser(UserRegisterDto userRegisterDto) {
    checkUserExists(userRegisterDto.getLogin());
    checkDoubledPassword(userRegisterDto.getPassword(), userRegisterDto.getDoubledPassword());
  }
}
