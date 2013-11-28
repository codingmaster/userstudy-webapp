package de.hpi.unipotsdam.thorben.dto;

public class ExceptionDto {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  public static ExceptionDto fromException(Exception e) {
    ExceptionDto dto = new ExceptionDto();
    dto.message = e.getMessage();
    return dto;
  }
}
