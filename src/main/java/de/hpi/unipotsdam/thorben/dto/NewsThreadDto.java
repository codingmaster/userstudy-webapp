package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.NewsThread;

public class NewsThreadDto {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public static NewsThreadDto fromNewsThread(NewsThread newsThread) {
    NewsThreadDto dto = new NewsThreadDto();
    dto.id = newsThread.getId();
    return dto;
  }
}
