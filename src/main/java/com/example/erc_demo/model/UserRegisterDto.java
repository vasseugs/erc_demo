package com.example.erc_demo.model;

import com.example.erc_demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterDto {

  private String login;
  private String password;
  private String doubledPassword;
  private String firstName;
  private String lastName;
  private String patronymic;
  private String avatarUrl;

  public UserEntity toEntity() {
    return UserEntity.builder()
        .login(this.login)
        .password(DigestUtils.sha256Hex(this.password))
        .firstName(this.firstName)
        .lastName(this.lastName)
        .patronymic(this.patronymic)
        .avatarUrl(this.avatarUrl)
        .build();
  }
}
