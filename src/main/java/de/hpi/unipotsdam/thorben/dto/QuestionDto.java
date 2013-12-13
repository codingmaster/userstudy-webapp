package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.QuestionItem;

public class QuestionDto {

  private Long id;
  private String content;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  
  public static QuestionDto fromQuestion(QuestionItem question) {
    QuestionDto dto = new QuestionDto();
    dto.id = question.getId();
    dto.content = question.getContent();
    return dto;
  }
}
