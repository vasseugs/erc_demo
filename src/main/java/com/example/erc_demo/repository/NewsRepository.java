package com.example.erc_demo.repository;

import com.example.erc_demo.entity.NewsArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsArticleEntity, Long> {

}
