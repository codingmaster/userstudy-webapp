package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.NewsThread;

public class NewsThreadDto {

  protected Long id;
  protected String title;
  protected String description;
  protected Number itemCount;

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
  
  public Number getItemCount() {
    return itemCount;
  }

  public void setItemCount(Number itemCount) {
    this.itemCount = itemCount;
  }

  public static NewsThreadDto fromNewsThread(NewsThread newsThread) {
    NewsThreadDto dto = new NewsThreadDto();
    dto.id = newsThread.getId();
    dto.title = newsThread.getTitle();
    dto.description = newsThread.getDescription();
    return dto;
  }
  
  public static NewsThreadDto fromNewsThread(NewsThread newsThread, Number itemCount) {
    NewsThreadDto dto = fromNewsThread(newsThread);
    dto.itemCount = itemCount;
    return dto;
  }
  
}
