package com.example.erc_demo.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsArticleDto {

  @NotBlank
  private String header;

  @NotBlank
  private String article;
}
