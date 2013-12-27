package de.hpi.unipotsdam.thorben.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "article")
public class Article {

  protected Long id;
  private String title;
  private String content;
  private String htmlContent;
  
  private String abstractContent;
  private String htmlAbstractContent;
  
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
  
  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(name = "content")
  public String getContent() {
    return content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(name = "html_content")
  public String getHtmlContent() {
    return htmlContent;
  }

  public void setHtmlContent(String htmlContent) {
    this.htmlContent = htmlContent;
  }

  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(name = "abstract")
  public String getAbstractContent() {
    return abstractContent;
  }

  public void setAbstractContent(String abstractContent) {
    this.abstractContent = abstractContent;
  }

  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(name = "html_abstract")
  public String getHtmlAbstractContent() {
    return htmlAbstractContent;
  }
  
  public void setHtmlAbstractContent(String htmlAbstractContent) {
    this.htmlAbstractContent = htmlAbstractContent;
  }

  @Column(name = "publication_date")
  public Timestamp getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Timestamp publicationDate) {
    this.publicationDate = publicationDate;
  }
  
  

}
