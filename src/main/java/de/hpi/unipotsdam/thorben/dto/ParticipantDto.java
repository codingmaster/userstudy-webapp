package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.Participant;

public class ParticipantDto {

  private String id;
  private String name;
  
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  public void updateFrom(Participant participant) {
    id = participant.getId();
    name = participant.getName();
  }
}
