package com.example.erc_demo.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthDto {

  @NotBlank(message = "Login can't be empty")
  private String login;

  @NotBlank(message = "Password can't be empty")
  private String password;
}
