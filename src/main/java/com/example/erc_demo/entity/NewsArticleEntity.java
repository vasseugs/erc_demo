package com.example.erc_demo.entity;

import com.example.erc_demo.model.NewsArticleDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsArticleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "header")
  private String header;

  @Column(name = "article")
  private String article;

  public NewsArticleDto toDto() {
    return NewsArticleDto.builder()
        .header(this.header)
        .article(this.article)
        .build();
  }
}
