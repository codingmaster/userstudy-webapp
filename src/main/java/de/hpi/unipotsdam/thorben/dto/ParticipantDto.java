package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.Participant;

public class ParticipantDto {

  private String id;
  private String username;
  
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  
  public void updateFrom(Participant participant) {
    id = participant.getId();
    username = participant.getName();
  }
}
