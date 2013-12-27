package de.hpi.unipotsdam.thorben.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "participant")
public class Participant {

  private String id;
  private String email;
  private int age;
  private String publications;
  private String consumptionBehavior;
  
  @Id
  @GeneratedValue(generator="uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
  @Column
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  
  @Column
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  
  @Column(length = 256)
  public String getPublications() {
    return publications;
  }
  public void setPublications(String publications) {
    this.publications = publications;
  }
  
  @Column(length = 256)
  public String getConsumptionBehavior() {
    return consumptionBehavior;
  }
  public void setConsumptionBehavior(String consumptionBehavior) {
    this.consumptionBehavior = consumptionBehavior;
  }
  
  
}
