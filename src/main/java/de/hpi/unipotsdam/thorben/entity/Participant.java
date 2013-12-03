package de.hpi.unipotsdam.thorben.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "participant")
public class Participant {

  private String id;
  private String name;
  private String email;
  
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
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @Column
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
