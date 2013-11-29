package de.hpi.unipotsdam.thorben.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hpi.unipotsdam.thorben.entity.Article;
import de.hpi.unipotsdam.thorben.entity.NewsThread;

public class NewsThreadDto {

  private Long id;
  private String title;
  private String description;
  private List<Long> articles;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Long> getArticles() {
    return articles;
  }

  public void setArticles(List<Long> articles) {
    this.articles = articles;
  }

  public static NewsThreadDto fromNewsThread(NewsThread newsThread) {
    NewsThreadDto dto = new NewsThreadDto();
    dto.id = newsThread.getId();
    dto.title = newsThread.getTitle();
    dto.description = newsThread.getDescription();
    
    List<Long> articleIds = new ArrayList<Long>();
    for (Article article : newsThread.getArticles()) {
      articleIds.add(article.getId());
    }
    
    dto.articles = articleIds;
    
    return dto;
  }
  
}
