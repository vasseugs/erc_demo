package com.example.erc_demo.model;

import com.example.erc_demo.entity.UserEntity;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.URL;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterDto {

  @NotBlank(message = "Login can't be empty")
  private String login;

  @NotBlank(message = "Specify password")
  private String password;

  @NotBlank(message = "Specify password one more time")
  private String repeatedPassword;

  @NotBlank(message = "Specify first name")
  private String firstName;

  @NotBlank(message = "Specify last name")
  private String lastName;

  private String patronymic;

  @URL(message = "Invalid avatar URL")
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
