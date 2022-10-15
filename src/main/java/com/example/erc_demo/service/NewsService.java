package com.example.erc_demo.service;

import com.example.erc_demo.model.NewsArticleDto;
import java.util.List;

public interface NewsService {

  List<NewsArticleDto> getAllNews();
}
