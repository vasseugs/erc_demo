package com.example.erc_demo.rest;

import com.example.erc_demo.model.UserAuthDto;
import com.example.erc_demo.model.UserRegisterDto;
import com.example.erc_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "users/")
public class UserController {

  private final UserService userService;

  @PostMapping(path = "register")
  public ResponseEntity<Void> registerNewUser(@RequestBody UserRegisterDto userRegisterDto) {
    userService.registerNewUser(userRegisterDto);
    return ResponseEntity.ok().build();
  }

  @PostMapping(path = "authorize")
  public ResponseEntity<Void> authorizeUser(@RequestBody UserAuthDto userAuthDto) {
    userService.authorizeUser(userAuthDto);
    return ResponseEntity.ok().build();
  }
}
