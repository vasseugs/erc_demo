package com.example.erc_demo.rest;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.erc_demo.initializer.AbstractTestContainers;
import com.example.erc_demo.model.UserAuthDto;
import com.example.erc_demo.model.UserRegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.jdbc.SqlMergeMode.MergeMode;

public class UserRestTest extends AbstractTestContainers {

  @Test
  void registerNewUser() {
    var newUser = UserRegisterDto.builder()
        .login("login")
        .password("password")
        .repeatedPassword("password")
        .firstName("firstName")
        .lastName("lastName")
        .patronymic("patronymic")
        .avatarUrl("http://www.google.com/hello")
        .build();

    var createNewUser = testRestTemplate.exchange(
        "/users/register", HttpMethod.POST,
        new HttpEntity<>(newUser), Void.class);
    assertThat(createNewUser.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  @Sql(scripts = "/sql/user.sql")
  @SqlMergeMode(value = MergeMode.MERGE)
  void authorizeUser() {
    var userAuthDto = UserAuthDto.builder()
        .login("login")
        .password("password")
        .build();

    var authorizeUser = testRestTemplate.exchange(
        "/users/authorize", HttpMethod.POST,
        new HttpEntity<>(userAuthDto), Void.class);
    assertThat(authorizeUser.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
