package de.hpi.unipotsdam.thorben.dto;

import java.util.Date;

import de.hpi.unipotsdam.thorben.entity.Article;

public class ArticleDto {

  private Long id;
  private String title;
  private String content;
  private Date publicationDate;
  
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
  
  public Date getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  public static ArticleDto fromArticle(Article article) {
    ArticleDto dto = new ArticleDto();
    dto.id = article.getId();
    dto.title = article.getTitle();
    dto.content = article.getContent();
    dto.publicationDate = article.getPublicationDate();
    return dto;
  }
}
