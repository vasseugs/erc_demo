package com.example.erc_demo.service.impl;

import com.example.erc_demo.entity.NewsArticleEntity;
import com.example.erc_demo.model.NewsArticleDto;
import com.example.erc_demo.repository.NewsRepository;
import com.example.erc_demo.service.NewsService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

  private final NewsRepository newsRepository;

  public List<NewsArticleDto> getAllNews() {
    return newsRepository.findAll()
        .stream()
        .map(NewsArticleEntity::toDto)
        .collect(Collectors.toList());
  }

}
