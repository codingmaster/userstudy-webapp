package de.hpi.unipotsdam.thorben.dto;

import de.hpi.unipotsdam.thorben.entity.Participant;

public class ParticipantDto {

  private String id;
  private String email;
  private int age;
  private String publications;
  private String consumptionBehavior;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public String getPublications() {
    return publications;
  }
  public void setPublications(String publications) {
    this.publications = publications;
  }
  public String getConsumptionBehavior() {
    return consumptionBehavior;
  }
  public void setConsumptionBehavior(String consumptionBehavior) {
    this.consumptionBehavior = consumptionBehavior;
  }
  
  public void updateFrom(Participant participant) {
    id = participant.getId();
    age = participant.getAge();
    publications = participant.getPublications();
    consumptionBehavior = participant.getConsumptionBehavior();
    email = participant.getEmail();
  }
}
