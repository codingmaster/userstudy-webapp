package de.hpi.unipotsdam.thorben.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "thread")
public class NewsThread {

  private Long id;
  private String title;
  private String description;
  private Set<ThreadItem> items;
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Lob
  @Column(name = "description", length = 256)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @OneToMany(mappedBy = "thread", fetch = FetchType.LAZY)
  public Set<ThreadItem> getItems() {
    return items;
  }

  public void setItems(Set<ThreadItem> items) {
    this.items = items;
  }
}
