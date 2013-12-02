package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.Article;

public class ArticleDto {

  private Long id;
  private String title;
  private String content;
  
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public static ArticleDto fromArticle(Article article) {
    ArticleDto dto = new ArticleDto();
    dto.id = article.getId();
    dto.title = article.getTitle();
    dto.content = article.getContent();
    return dto;
  }
}
