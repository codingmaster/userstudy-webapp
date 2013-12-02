package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.Rating;

public class RatingDto {

  private Integer rating;
  private Long itemId;
  
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
  
  public static RatingDto fromRating(Rating rating) {
    RatingDto dto = new RatingDto();
    dto.itemId = rating.getThreadItem().getId();
    dto.rating = rating.getRating();
    return dto;
  }
  
}
