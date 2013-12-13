package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.Rating;

public class RatingDto {

  private Long id;
  private Integer rating;
  private Long itemId;
  private Long questionId;
  
  public Integer getRating() {
    return rating;
  }
  public void setRating(Integer rating) {
    this.rating = rating;
  }
  public Long getItemId() {
    return itemId;
  }
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Long getQuestionId() {
    return questionId;
  }
  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }
  
  public static RatingDto fromRating(Rating rating) {
    RatingDto dto = new RatingDto();
    dto.itemId = rating.getThreadItem().getId();
    dto.rating = rating.getRating();
    dto.id = rating.getId();
    dto.questionId = rating.getQuestionItem().getId();
    return dto;
  }
  
  public void updateFrom(Rating rating) {
    this.itemId = rating.getThreadItem().getId();
    this.rating = rating.getRating();
    this.id = rating.getId();
    this.questionId = rating.getQuestionItem().getId();
  }
  
}
