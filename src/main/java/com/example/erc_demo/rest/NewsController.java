package com.example.erc_demo.rest;

import com.example.erc_demo.model.NewsArticleDto;
import com.example.erc_demo.service.NewsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "news/")
public class NewsController {

  private final NewsService newsService;

  @GetMapping(path = "all")
  public ResponseEntity<List<NewsArticleDto>> getAllNews() {
    return ResponseEntity.ok().body(newsService.getAllNews());
  }

}
