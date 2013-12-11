package de.hpi.unipotsdam.thorben.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "article")
public class Article {

  private Long id;
  private String title;
  private String content;
  private String abstractContent;
  private Timestamp publicationDate;
  
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
  
  @Column(name = "content")
  public String getContent() {
    return content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  @Column(name = "abstract")
  public String getAbstractContent() {
    return abstractContent;
  }

  public void setAbstractContent(String abstractContent) {
    this.abstractContent = abstractContent;
  }

  @Column(name = "publication_date")
  public Timestamp getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Timestamp publicationDate) {
    this.publicationDate = publicationDate;
  }
  
  

}
