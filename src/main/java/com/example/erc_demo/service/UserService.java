package com.example.erc_demo.service;

import com.example.erc_demo.model.UserAuthDto;
import com.example.erc_demo.model.UserRegisterDto;

public interface UserService {

  void registerNewUser(UserRegisterDto userRegisterDto);

  void authorizeUser(UserAuthDto userAuthDto);

  void checkUserExists(Long id);
}
