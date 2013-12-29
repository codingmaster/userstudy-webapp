package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.ThreadItem;

public class ThreadItemDto {

  private Long id;
  private Integer logicalOrder;
  private ArticleDto article;
  
  public Long getId() {
    return id;
  }
 
  public void setId(Long id) {
    this.id = id;
  }

  public Integer getLogicalOrder() {
    return logicalOrder;
  }

  public void setLogicalOrder(Integer logicalOrder) {
    this.logicalOrder = logicalOrder;
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
    dto.logicalOrder = item.getLogicalOrder();
    dto.article = ArticleDto.fromArticle(item.getArticle());
    return dto;
  }
}
