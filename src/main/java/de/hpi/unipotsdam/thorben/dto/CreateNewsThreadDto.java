package de.hpi.unipotsdam.thorben.dto;

import java.util.List;

public class CreateNewsThreadDto extends NewsThreadDto {

  private List<Long> articleIds;

  public List<Long> getArticleIds() {
    return articleIds;
  }

  public void setArticleIds(List<Long> articleIds) {
    this.articleIds = articleIds;
  }
}
