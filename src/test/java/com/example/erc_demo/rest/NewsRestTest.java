package com.example.erc_demo.rest;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.erc_demo.initializer.AbstractTestContainers;
import com.example.erc_demo.model.NewsArticleDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.jdbc.SqlMergeMode.MergeMode;

public class NewsRestTest extends AbstractTestContainers {

  @Test
  @Sql(scripts = "/sql/news_feed.sql")
  @SqlMergeMode(value = MergeMode.MERGE)
  void getAllNews() {
    var getAllNews = testRestTemplate.exchange(
        "/news/all", HttpMethod.GET,
        new HttpEntity<>(null), new ParameterizedTypeReference<List<NewsArticleDto>>() {});
    assertThat(getAllNews.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(getAllNews.getBody()).hasSize(2);
  }
}
