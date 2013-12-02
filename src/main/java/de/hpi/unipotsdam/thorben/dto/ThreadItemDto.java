package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.ThreadItem;

public class ThreadItemDto {

  private Long id;
  private ArticleDto article;
  
  public Long getId() {
    return id;
  }
 
  public void setId(Long id) {
    this.id = id;
  }

  public ArticleDto getArticle() {
    return article;
  }

  public void setArticle(ArticleDto article) {
    this.article = article;
  }

  public static ThreadItemDto fromThreadItem(ThreadItem item) {
    ThreadItemDto dto = new ThreadItemDto();
    dto.id = item.getId();
    dto.article = ArticleDto.fromArticle(item.getArticle());
    return dto;
  }
}
